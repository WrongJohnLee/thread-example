package input_output;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * 传统io与nio
 */
public class NormalAndNioReadTest {

    /**
     * 传统io不能读写一起
     * 只能先读到缓冲区
     * 处理完再读
     * 单向操作
     */
    @Test
    public void NormalRead() {
        //Buffered表示读到缓冲区，避免一直切换系统与用户态。这里用到了装饰者模式，重写了read方法。
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream("src/test.txt"))) {
            byte[] buffer = new byte[1024];
            //读到缓冲区，并返回剩余字节大小
            int readByte = inputStream.read(buffer);
            //等于-1表示数据读完了
            while (readByte != -1) {
                for (int i = 0; i < readByte; i++) {
                    System.out.println((char)buffer[i]);
                }
                readByte = inputStream.read(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * NIO通过管道、缓冲的方式处理IO
     * 支持读写双向操作
     */
    public static void main(String[] args) {
        //获取文件的读写模式，mode支持只读、只写、读写
        try {
            RandomAccessFile file = new RandomAccessFile("/home/i999/IdeaProjects/thread-example/io/src/test.txt", "rw");
            //获取通道
            FileChannel channel = file.getChannel();
            //申请直接内存
            ByteBuffer buf = ByteBuffer.allocate(1024);
            //管道读取到缓冲区
            long dataSize = channel.read(buf);
            while (dataSize != -1) {
                //设置缓冲区的limit、容量大小
                buf.flip();
                while (buf.hasRemaining()) {
                    System.out.println((char)buf.get());
                }
                //清除缓冲区，设置从0开始
                buf.clear();
                //读新数据到缓冲区
                dataSize = channel.read(buf);
            }
            //可以在管道中直接写入
//            new Thread(new FileWriteRunner(channel)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static class FileWriteRunner implements Runnable {
        private volatile FileChannel channel;

        public FileWriteRunner(FileChannel channel) {
            this.channel = channel;
        }

        @Override
        public void run() {
            int loopSize = 0;
            while (true) {
                loopSize++;
                try {
                    ByteBuffer writeByteBuf = Charset.forName("utf8").encode(String.format("第%s次写入666",loopSize));
                    channel.write(writeByteBuf);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}

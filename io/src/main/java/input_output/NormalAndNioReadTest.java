package input_output;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * 传统io与nio
 * <p>
 *     NIO各个类概念
 *     https://www.cnblogs.com/fwnboke/p/8529604.html
 * </p>
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
     * NIO通过通道、缓冲的方式处理IO
     * 支持读写双向操作
     */
    @Test
    public void NIOReadAndWriteTest() {
        //获取文件的读写模式，mode支持只读(r)、只写(w)、读写(rw)
        try {
            RandomAccessFile file = new RandomAccessFile("/home/i999/IdeaProjects/thread-example/io/src/test.txt", "rw");
            //获取通道
            FileChannel channel = file.getChannel();
            //申请指定大小的内存空间，默认是堆内存。还可以allocateDirect直接内存（堆外内存）
            ByteBuffer buf = ByteBuffer.allocate(1024);
            //通道读取到缓冲区，并设置将buf的大小刷新
            long dataSize = channel.read(buf);
            while (dataSize != -1) {
                //确定缓冲区数据的起始点和终止点，为输出数据做准备(即写入通道)。此时：limit = position，position = 0。
                buf.flip();
                while (buf.hasRemaining()) {
                    //get的方式，将当前位置标记向前一位
                    System.out.println((char)buf.get());
                }
                //缓冲区初始化，准备再次接收新数据到缓冲区。position = 0，limit = capacity。
                buf.clear();
                //读新数据到缓冲区，ByteBuffer.put()，并将buf容量设置
                dataSize = channel.read(buf);
            }
            //可以在通道中直接写入
//            new Thread(new FileWriteRunner(channel)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 内存映射拷贝文件
     * 如果是大文件，效率会非常高
     * @link MappedByteBuffer
     */
    @Test
    public void NIOMapperByteBufTest() throws IOException {
        File in = new File("src/in.txt");
        File out = new File("src/out.txt");
        FileChannel inChannel = new FileInputStream(in).getChannel();
        FileChannel outChannel = new FileOutputStream(out).getChannel();
        MappedByteBuffer mappedByteBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        outChannel.write(mappedByteBuffer);
        mappedByteBuffer.clear();
        //finally close
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
                    //需要转成字节码
                    ByteBuffer writeByteBuf = Charset.forName("utf8").encode(String.format("第%s次写入666",loopSize));
                    //写入通道
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

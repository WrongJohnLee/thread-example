package arrayblockqueue;

public class ArrayQueueTest {

    @org.junit.Test
    public void put() {
        final ArrayQueue<Integer> stringArrayQueue = new ArrayQueue<>(3);
        new Thread(() -> {
            stringArrayQueue.put(1);
        }).start();

        new Thread(() -> {
            stringArrayQueue.put(1);
        }).start();

        new Thread(() -> {
            stringArrayQueue.put(1);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        //队列已满，需要等待消费空出才能插入
        new Thread(() -> {
            long start = System.currentTimeMillis();
            stringArrayQueue.put(1);
            long end = System.currentTimeMillis() - start;
            System.out.println("wait to put"+end);
        }).start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //消费了，会通知put线程进行put操作
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"消费："+String.valueOf(stringArrayQueue.get()));
        }).start();
    }

    @org.junit.Test
    public void get() {


    }
}

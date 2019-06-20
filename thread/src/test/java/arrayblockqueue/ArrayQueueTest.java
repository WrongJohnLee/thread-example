package arrayblockqueue;

import org.junit.Test;

public class ArrayQueueTest {

    @org.junit.Test
    public void put() {
        final ArrayQueue<Integer> stringArrayQueue = new ArrayQueue<>(3);
        new Thread(() -> {
            stringArrayQueue.put(1);
        }).start();

        new Thread(() -> {
            stringArrayQueue.put(2);
        }).start();

        new Thread(() -> {
            stringArrayQueue.put(3);
        }).start();

        //队列已满，等待消费了队列有空间，通知等待fullLock的线程
        new Thread(() -> {
            long start = System.currentTimeMillis();
            stringArrayQueue.put(1);
            long end = System.currentTimeMillis() - start;
            System.out.println("wait to put" + end);
        }).start();

        //模拟消费场景
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //消费了队列有空间，会通知等待fullLock的put线程进行put操作
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "消费：" + stringArrayQueue.get());
        }).start();
    }

    @org.junit.Test
    public void get() {
        final ArrayQueue<Integer> stringArrayQueue = new ArrayQueue<>(3);
        stringArrayQueue.put(1);
        stringArrayQueue.put(2);
        stringArrayQueue.put(3);

        new Thread(() -> {
            System.out.println(Thread.currentThread() + " get : " + stringArrayQueue.get());
        }).start();

        new Thread(() -> {
            System.out.println(Thread.currentThread() + " get : " + stringArrayQueue.get());
        }).start();

        new Thread(() -> {
            System.out.println(Thread.currentThread() + " get : " + stringArrayQueue.get());
        }).start();

        //队列已空，需要等待生产，通知等待emptyLock的线程去消费
        new Thread(() -> {
            long start = System.currentTimeMillis();
            System.out.println(Thread.currentThread() + " get : " + stringArrayQueue.get());
            long end = System.currentTimeMillis() - start;
            System.out.println("wait to get" + end);
        }).start();

        //模拟生产时间
        try {
            Thread.sleep(5000);
            //stringArrayQueue.put(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //这里生产数据，通知等待emptyLock的get线程
        stringArrayQueue.put(6666);

        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //消费了，会通知put线程进行put操作
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"消费："+ stringArrayQueue.get());
        }).start();*/

    }

    @Test
    public void concurrentPutThenGet() throws InterruptedException {
        ArrayQueue<Integer> queue = new ArrayQueue<>(299);

        Thread t1 = new Thread(
                () -> {
                    for (int i = 0; i < 100; i++) {
                        queue.put(i);
                    }
                }
        );

        Thread t2 = new Thread(
                () -> {
                    for (int i = 0; i < 100; i++) {
                        queue.put(i);
                    }
                }
        );

        Thread t3 = new Thread(
                () -> {
                    for (int i = 0; i < 100; i++) {
                        queue.put(i);
                    }
                }
        );

        Thread t4 = new Thread(
                () -> {
                    System.out.println("==========" + queue.get());
                }

        );

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("========= size :" + queue.getCount());
    }
}

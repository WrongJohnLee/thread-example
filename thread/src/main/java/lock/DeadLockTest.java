package lock;

import java.util.concurrent.CountDownLatch;

/**
 * 死锁的核心
 * 一个方法需要持有A B锁
 * 另外一个方法需要持有B A锁
 * 当线程1持有A锁，线程2持有B锁。
 * 但是线程1要申请B锁，线程2要申请A锁，就互相僵持了导致死锁。
 * @author by johnny
 * @since 2020/6/30 15:33
 */
public class DeadLockTest {
    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();
        // 模拟两个线程同时进入第二个锁
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(
                () -> {
                    synchronized (lock1) {
                        System.out.println("持有锁1");
                        try {
                            // 等待另一线程持有lock2
                            countDownLatch.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        synchronized (lock2) {
                            System.out.println("持有锁1、2");
                        }
                    }
                }
        ).start();

        new Thread(
                () -> {
                    synchronized (lock2) {
                        System.out.println("持有锁2");
                        // 模拟两个线程同时持有锁，让先跑的线程等待不要把lock1释放掉
                        countDownLatch.countDown();
                        synchronized (lock1) {
                            System.out.println("持有锁1、2");
                        }
                    }
                }
        ).start();
    }
}

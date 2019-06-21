package countdownlatch;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {

    @Test
    public void test() throws InterruptedException {
        CountDownLatch count = new CountDownLatch(4);
        //三缺一

        Thread t1 = new Thread(
                () -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count.countDown();
                    System.out.println("玩家1进来咯");
                }
        );
        Thread t2 = new Thread(
                () -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count.countDown();
                    System.out.println("玩家2进来咯");
                }
        );
        Thread t3 = new Thread(
                () -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count.countDown();
                    System.out.println("玩家3进来咯");
                }
        );

        Thread t4 = new Thread(
                () -> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count.countDown();
                    System.out.println("玩家4进来咯");
                }
        );

        t1.start();
//        t1.join();
//        System.out.println(count.getCount());
        t2.start();
//        t2.join();
//        System.out.println(count.getCount());
        t3.start();
//        t3.join();
//        System.out.println(count.getCount());
        t4.start();
//        t4.join();
//        System.out.println(count.getCount());

        try {
            while (count.getCount() != 0) {
                System.out.println("缺人啊，来来来，入局");
            }
            count.await();
            System.out.println("开始咯");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

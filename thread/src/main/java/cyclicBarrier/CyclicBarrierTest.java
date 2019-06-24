package cyclicBarrier;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3);
        new Thread(
                () ->
                {
                    System.out.println("before1");
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println("走你-------1");
                }
        ).start();

        new Thread(
                () ->
                {
                    System.out.println("before2");
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println("走你-------2");
                }
        ).start();

        new Thread(
                () ->
                {
                    System.out.println("before3");
                    try {
                        Thread.sleep(3000);
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println("走你-------3");
                }
        ).start();
    }

    @Test
    public void test() {
        CyclicBarrier barrier = new CyclicBarrier(3);
        new Thread(
                () ->
                        {
                            System.out.println("before1");
                            try {
                                barrier.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            } catch (BrokenBarrierException e) {
                                e.printStackTrace();
                            }
                            System.out.println("走你-------1");
                        }
        ).start();

        new Thread(
                () ->
                {
                    System.out.println("before2");
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println("走你-------2");
                }
        ).start();

        new Thread(
                () ->
                {
                    System.out.println("before3");
                    try {
                        Thread.sleep(3000);
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println("走你-------3");
                }
        ).start();
    }
}

package pool;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PoolTest {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        ExecutorService po = Executors.newFixedThreadPool(3);
        Runnable t1 = () -> {
            System.out.println("--------1---------");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("after");
        };
        Runnable t2 = () -> {
            System.out.println("--------3---------");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("after");
        };
        Runnable t3 = () -> {
            System.out.println("--------2---------");
            try {
                Thread.sleep(3000);
                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("after");
        };
        long start = System.currentTimeMillis();
        Runnable t4 = () -> {
            System.out.println("--------4---------" + (System.currentTimeMillis() - start));
        };
        po.execute(t1);
        po.execute(t2);
        po.execute(t3);
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        po.execute(t4);
        po.shutdown();
    }

}

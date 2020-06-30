package lock.reentrantLock;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    @Test
    public void testCondition() {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        new Thread(
                () ->{
                    reentrantLock.lock();
                    System.out.println("-----waiting------");
                    try {
                        condition.await(3000, TimeUnit.MILLISECONDS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("-----finish wait------");
                    reentrantLock.unlock();
                }
        ).start();

        new Thread(
                () -> {
                    reentrantLock.lock();
                    System.out.println("-----notify-----");
                    condition.signalAll();
                    System.out.println("-----notify finish-----");
                    reentrantLock.unlock();
                }
        ).start();
    }
}

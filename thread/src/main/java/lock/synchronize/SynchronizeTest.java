package lock.synchronize;

/**
 * 多线程竞争，拿到锁后，进入monitorenter，其他线程进入AQS等待，
 * 当拿到锁的线程执行到monitorexit后，其他线程继续竞争抢得monitor执行
 * javac **.java
 * javap -c **
 */
public class SynchronizeTest {
    public static void main(String[] args) {
        synchronized (SynchronizeTest.class) {//monitorenter
            System.out.println("synchronized");
        }//monitorexit
    }
}

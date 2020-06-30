package lock.writereadlock;

import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class WriteReadLockTest {

    @Test
    public void testTryLock() throws InterruptedException {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock() ;
        new Thread(
                () -> {
                    System.out.println("waiting for write unlock");
                    reentrantReadWriteLock.writeLock().lock();
                    try {
                        System.out.println("read locking,write wait");
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        reentrantReadWriteLock.writeLock().unlock();
                    }
                }
        ).start();


        new Thread(
                () -> {
                    System.out.println("waiting for read or write unlock");
                    reentrantReadWriteLock.writeLock().lock();
                    try {
                        System.out.println("write locking,read and write wait");
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        reentrantReadWriteLock.writeLock().unlock();
                    }
                }
        ).start();

        new Thread(
                () -> {
                    System.out.println("waiting for read or write unlock");
                    reentrantReadWriteLock.writeLock().lock();
                    try {
                        System.out.println("write locking,read and write wait");
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        reentrantReadWriteLock.writeLock().unlock();
                    }
                }
        ).start();

        new Thread(
                () -> {
                    System.out.println("waiting for write unlock");
                    reentrantReadWriteLock.writeLock().lock();
                    try {
                        System.out.println("read locking,write wait");
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        reentrantReadWriteLock.writeLock().unlock();
                    }
                }
        ).start();

        long start = System.currentTimeMillis();
        reentrantReadWriteLock.readLock().tryLock(4000, TimeUnit.MILLISECONDS);
        System.out.println("--------lock wait : "+(System.currentTimeMillis()-start));
    }

    @Test
    public void test() {
        MockCache cache = new MockCache();
        new Thread(
                () -> {
                    cache.write("1", 1);
                }
        ).start();
        new Thread(
                () -> {
                    cache.get("1");
                }
        ).start();

        new Thread(
                () -> {
                    cache.get("1");
                }
        ).start();

        new Thread(
                () -> {
                    cache.write("2", 2);
                }
        ).start();
    }

    class MockCache {
        private final Map<String, Object> cache = new ConcurrentHashMap<>();
        private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        public void write(String key, Object value) {
            try {
                readWriteLock.writeLock().tryLock(4000, TimeUnit.SECONDS);
                cache.put(key, value);
                System.out.println(Thread.currentThread().getName() + " write locking, any behavior can't in");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                readWriteLock.writeLock().unlock();
                System.out.println(Thread.currentThread().getName() + " write unlock, any behavior can in");
            }
        }

        public void get(String key) {
            try {
                readWriteLock.readLock().tryLock(4000, TimeUnit.SECONDS);
                cache.get(key);
                System.out.println(Thread.currentThread().getName() + " read locking, write behavior can't in");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                readWriteLock.readLock().unlock();
                System.out.println(Thread.currentThread().getName() + " read unlock, write behavior can in");
            }
        }

    }

}

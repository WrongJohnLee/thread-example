package writereadlock;

/**
 * 乞丐版读写锁
 */
public class SimpleReadWritLock{
    /** 读线程数 **/
    private volatile int readers = 0;
    /** 写线程数 **/
    private volatile int writers = 0;


    /**
     * 读锁，如果有写线程在写则等待
     */
    public void readLock() {
        while (writers > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ++readers;
    }

    /**
     * 读锁释放，并通知通知所有等待线程
     */
    public void readUnLock() {
        --readers;
        notifyAll();
    }


    /**
     * 写锁，当有读或者写线程则等待
     */
    public void writeLock() {
        while (readers > 0 || writers > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        ++writers;
    }

    /**
     * 写锁释放，并通知通知所有等待线程
     */
    public void writeUnLock() {
        --writers;
        notifyAll();
    }
}

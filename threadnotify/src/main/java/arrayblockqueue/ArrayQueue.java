package arrayblockqueue;

/**
 * 线程通讯
 * @author john
 *
 */
public class ArrayQueue<T> {
    private Object[] items;

    private int count = 0;

    private final Object emptyLock = new Object();

    private final Object fullLock = new Object();

    private int putIndex;

    private int getIndex;

    public ArrayQueue(int size) {
        this.items = new Object[size];
    }


    /**
     * 从队列尾部写入数据
     * @param t
     */
    public void put(T t) {
        //抢到满锁
        synchronized (fullLock) {
            //当队列数量满了，阻塞等待
            while (count == items.length) {
                try {
                    fullLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


        //抢到空锁
        synchronized (emptyLock) {
            //队列尾部写入
            items[putIndex] = t;
            count++;

            putIndex++;
            if (putIndex == items.length) {
                //超过数组长度后需要从头开始
                putIndex = 0;
            }

            //如果插入数据表示不为空，则通知等待消费的线程去消费
            emptyLock.notify();

        }
    }

    /**
     * 从队列头部获取数据
     * @return
     */
    public T get() {
        //抢到空锁
        synchronized (emptyLock) {
            //当队列为空时，阻塞等待，等到有数据入队
            while (count == 0) {
                try {
                    emptyLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        //抢到满锁
        synchronized (fullLock) {
            //获取头部的数据，并设置为空，总数-1
            Object result = items[getIndex];
            items[getIndex] = null;
            count--;

            //头部元素定位+1，如果等于容器长度，从0第一个开始
            getIndex++;
            if (getIndex == items.length) {
                getIndex = 0;
            }
            //消费了，说明有空闲的位置可以写入，通知put线程等待的进行put
            fullLock.notify();

            return (T) result;
        }
    }


    public Object[] getItems() {
        return items;
    }

    public void setItems(Object[] items) {
        this.items = items;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Object getEmptyLock() {
        return emptyLock;
    }


    public int getPutIndex() {
        return putIndex;
    }

    public void setPutIndex(int putIndex) {
        this.putIndex = putIndex;
    }

    public int getGetIndex() {
        return getIndex;
    }

    public void setGetIndex(int getIndex) {
        this.getIndex = getIndex;
    }
}

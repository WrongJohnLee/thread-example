package volatile_;

import org.junit.Test;

/**
 * Volatile适用于一写多读的场景
 * 保持数据的可见性
 * 对于多写多读保证不了线程安全（操作依赖于当前值）
 */
public class VolatileTest {
    private static volatile int i = 0;

    static class Monitor{
        // 即时可见
        volatile boolean shutdownRequested;

        public void shutdown() { shutdownRequested = true; }

        public void doWork() {
            while (!shutdownRequested) {
                // do stuff
            }
        }
    }

    @Test
    public void moreWriteTest() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int j = 0; j < 10000; j++) {
                i += 1;
            }
        });

        Thread t2 = new Thread(() -> {
            for (int j = 0; j < 10000; j++) {
                i = i + 1;
            }
        });

        t1.start();

        t2.start();
        t1.join();
        t2.join();
        // 最终肯定小于20000，多线程写，volatile保证不了线程安全
        System.out.println(i);
    }



    @Test
    public void testRepeatSort() {
        RepeatSort repeatSort = new RepeatSort();

        for (int j = 0; j < 1000; j++) {
            new Thread(
                    () ->
                {
                    repeatSort.write();
                }
                ).start();

        }

        for (int j = 0; j < 1000; j++) {
            new Thread(
                    () ->
                    {
                        repeatSort.read();
                    }
            ).start();

        }
    }

    class RepeatSort{
        private int i = 0;
        private int j = 0;

        public void write() {
            i = 10;
            j = 1;
        }

        public void read() {
            if (j == 1) {
                System.out.println(i);
            }
        }
    }
}

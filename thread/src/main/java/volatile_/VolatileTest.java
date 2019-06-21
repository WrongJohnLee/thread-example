package volatile_;

import org.junit.Test;

public class VolatileTest {
    private volatile static int i = 0;

    public static void main(String[] args) throws InterruptedException {
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

        t1.join();
        t2.start();
        t2.join();
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

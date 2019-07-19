package pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ThreadPoolExecutorTest
 * @Description 线程测试，注意Worker类
 * @Author qzli
 * @Date 2019/7/18 22:20
 * @Version 1.0
 **/
public class ThreadPoolExecutorTest {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        pool.submit(() ->{
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test pool");
        });
    
        pool.submit(() ->{
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test pool");
        });
    
        /**
         * 因为设了两个核心线程数为2，
         * 前面两个一直没释放只能等待它们执行完去去queue里面拿
         */
        pool.submit(() ->{
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("test pool");
        });
    }
}

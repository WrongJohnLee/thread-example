import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @ClassName KafkaConsumerRunner
 * @Description TODO
 * @Author qzli
 * @Date 2019/7/27 15:00
 * @Version 1.0
 **/
public class KafkaConsumerRunner implements Runnable {
    private final AtomicBoolean closed = new AtomicBoolean(false);
    private final KafkaConsumer<String, String> consumer;
    
    public KafkaConsumerRunner(KafkaConsumer consumer) {
        this.consumer = consumer;
    }
    
    public void run() {
        try {
            consumer.subscribe(Arrays.asList("topic"));
            while (!closed.get()) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(10000));
                // Handle new records
                records.records("topic").forEach(
                        recode -> {
                            System.out.println(recode.value());
                        }
                );
                //同步offset，这里可以放在service同一事务，保存每个处理的record记录到数据库，保证幂等性。
                consumer.commitAsync();
            }
        } catch (WakeupException e) {
            // Ignore exception if closing
            if (!closed.get()) throw e;
        } finally {
            consumer.close();
        }
    }
    
    // Shutdown hook which can be called from a separate thread
    public void shutdown() {
        closed.set(true);
        consumer.wakeup();
    }
}

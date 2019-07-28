import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Properties;

/**
 * @ClassName ConsumerTest
 * @Description TODO
 * @Author qzli
 * @Date 2019/7/27 14:35
 * @Version 1.0
 **/
public class ConsumerTest {
    public static void main(String[] args) {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(getConsumerConfig());
        new Thread(new KafkaConsumerRunner(consumer)).start();
    }
    
    private static Properties getConsumerConfig() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.21.4:9092");
        props.put("group.id", "group-id");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return props;
    }
}


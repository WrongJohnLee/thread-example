import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.AuthorizationException;
import org.apache.kafka.common.errors.OutOfOrderSequenceException;
import org.apache.kafka.common.errors.ProducerFencedException;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ProducerTest
 * @Description TODO
 * @Author qzli
 * @Date 2019/7/27 14:34
 * @Version 1.0
 **/
public class ProducerTest {
    public static void main(String[] args) throws InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.21.4:9092");
        props.put("transactional.id", "my-transactional-id");
        Producer<String, String> producer = new KafkaProducer<>(props, new StringSerializer(), new StringSerializer());
    
        producer.initTransactions();
        for (;;) {
            try {
                producer.beginTransaction();
                for (int i = 0; i < 100; i++)
                    producer.send(new ProducerRecord<>("topic", Integer.toString(i), Integer.toString(i)),
                            new Callback() {
                                @Override
                                public void onCompletion(RecordMetadata metadata, Exception exception) {
                                    if (exception != null) {
                                        //insert to db
                                    }
                                }
                            });
                producer.commitTransaction();
            } catch (ProducerFencedException | OutOfOrderSequenceException | AuthorizationException e) {
                // We can't recover from these exceptions, so our only option is to close the producer and exit.
                producer.close();
            } catch (KafkaException e) {
                // For all other exceptions, just abort the transaction and try again.
                producer.abortTransaction();
            }
            
            Thread.sleep(1000);
        }
//        producer.close();
        
        
    }
    
    public static Properties getProducerConfig() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "192.168.21.4:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }
    
}

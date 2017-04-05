package kafkafib;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Properties;

public class FibProducer {

    private static final int DEFAULT_AMOUNT = 20;

    private int nums;

    public FibProducer(String amount) {
        try {
            nums = Integer.parseInt(amount);
        } catch (NumberFormatException e) {
            nums = DEFAULT_AMOUNT;
        }
    }

    public void run() {
        Properties properties = PropertiesLoader.propertiesAugmentedWithSystemOnes("/producer.properties");
        Producer<String, String> producer = new KafkaProducer<>(properties);
        String topicName = properties.getProperty("topic.name");
        BigInteger a = BigInteger.valueOf(-1);
        BigInteger b = BigInteger.valueOf(1);
        System.out.printf("Generating %s fibonacci numbers...%n", nums);
        for (int i = 0; i < nums; i++) {
            BigInteger c = a.add(b);
            producer.send(new ProducerRecord<>(topicName, c.toString()));
            a = b;
            b = c;
        }
    }

}

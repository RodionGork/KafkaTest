package kafkafib;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Properties;

public class FibConsumer {

    private final static int DEFAULT_OUTPUT_PERIOD = 5;

    private int outputPeriod;

    public FibConsumer(String argument) {
        try {
            outputPeriod = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            outputPeriod = DEFAULT_OUTPUT_PERIOD;
        }
    }

    public void run() {
        Properties properties = PropertiesLoader.propertiesAugmentedWithSystemOnes("/consumer.properties");
        String topicName = properties.getProperty("topic.name");
        Consumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Arrays.asList(topicName));
        BigInteger total = BigInteger.ZERO;
        int count = 0;
        System.out.println("Waiting for the data from the broker...");
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(300);
            for (ConsumerRecord<String, String> record : records) {
                total = total.add(new BigInteger(record.value()));
                count += 1;
                if (count % outputPeriod == 0) {
                    System.out.printf("after %s elements sum is %s%n", count, total);
                }
            }
        }
    }
}

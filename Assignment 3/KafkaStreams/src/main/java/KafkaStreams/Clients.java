package KafkaStreams;
import java.util.Collections;
import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Clients {

    public static Properties setupProducer(){
        // create instance for properties to access producer configs
        Properties props = new Properties();

        // assign localhost id
        props.put("bootstrap.servers", "localhost:9092");

        // set acknowledgements for producer requests.
        props.put("acks", "all");

        // if the request fails, the producer can automatically retry,
        props.put("retries", 0);

        // specify buffer size in config
        props.put("batch.size", 16384);

        // reduce the number of requests less than 0
        props.put("linger.ms", 1);

        // the buffer.memory controls the total amount of memory available to the producer for buffering.
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.LongSerializer");

        return props;
    }

    public static Properties setupConsumer(){
        // create instance for properties to access producer configs
        Properties props = new Properties();

        // assign localhost id
        props.put("bootstrap.servers", "localhost:9092");

        // set acknowledgements for producer requests.
        props.put("acks", "all");

        // if the request fails, the producer can automatically retry,
        props.put("retries", 0);

        // specify buffer size in config
        props.put("batch.size", 16384);

        // reduce the number of requests less than 0
        props.put("linger.ms", 1);

        // the buffer.memory controls the total amount of memory available to the producer for buffering.
        props.put("buffer.memory", 33554432);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaExampleConsumer");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.LongDeserializer");

        return props;
    }

    public static void main(String[] args) throws Exception{

        // assign topicName to string variable
        String creditsTopic = "Credits";
        String paymentsTopic = "Payments";
        String dbTopic = "DB Info";

        Producer<String, Long> producer = new KafkaProducer<>(setupProducer());

        Consumer<String, Long> consumer = new KafkaConsumer<>(setupConsumer());
        consumer.subscribe(Collections.singletonList(dbTopic));

        try {
            while (true) {

                //  read from database
                ConsumerRecords<String, Long> records = consumer.poll(Long.MAX_VALUE);
                for (ConsumerRecord<String, Long> record: records){
                    System.out.println(record.key() + " => " + record.value());
                }

                //  send to Credits
                if ((new Random()).nextInt(2) == 1) {
                    System.out.println("credits");
                    producer.send(new ProducerRecord<String, Long>(creditsTopic, Integer.toString(1), (long) 1));
                    System.out.println("Sending message to topic " + creditsTopic);

                }

                //  send to Payments
                if ((new Random()).nextInt(2) == 0) {
                    System.out.println("payments");
                    producer.send(new ProducerRecord<String, Long>(paymentsTopic, Integer.toString(2), (long) 2));
                    System.out.println("Sending message to topic " + paymentsTopic);

                }

                Thread.sleep(500);

            }
        } finally {
            producer.close();
            consumer.close();
        }

    }

}
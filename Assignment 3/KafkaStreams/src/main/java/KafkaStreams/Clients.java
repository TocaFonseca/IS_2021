package KafkaStreams;
import java.sql.Timestamp;
import java.util.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.json.JSONObject;

public class Clients {

    public static Properties consumerProps(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "127.0.0.1:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaExampleConsumer");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return props;
    }

    public static Properties producerProps(){
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }

    public static void main(String[] args) throws Exception{

        Random rand = new Random();
        ObjectMapper mapper = new ObjectMapper();

        String clientTopic = "client";
        Consumer<String, String> clientConsumer = new KafkaConsumer<>(consumerProps());
        clientConsumer.subscribe(Collections.singletonList(clientTopic));

        String currencyTopic = "currency";
        Consumer<String, String> currencyConsumer = new KafkaConsumer<>(consumerProps());
        currencyConsumer.subscribe(Collections.singletonList(currencyTopic));

        String creditTopic = "credit";
        Producer<String, String> creditProducer = new KafkaProducer<>(producerProps());

        String paymentTopic = "payment";
        Producer<String, String> paymentProducer = new KafkaProducer<>(producerProps());

        try {
            while (true) {

                // get clients list from database
                ConsumerRecords<String, String> clientRecords = clientConsumer.poll(Long.MAX_VALUE);
                List<JSONObject> clientList = new ArrayList<>();
                for (ConsumerRecord<String, String> record : clientRecords) {
                    JSONObject json = new JSONObject(record.value());
                    clientList.add(json.getJSONObject("payload"));
                }

                // get currencies list from database
                ConsumerRecords<String, String> currencyRecords = currencyConsumer.poll(Long.MAX_VALUE);
                List<JSONObject> currencyList = new ArrayList<>();
                for (ConsumerRecord<String, String> record : currencyRecords) {
                    JSONObject json = new JSONObject(record.value());
                    currencyList.add(json.getJSONObject("payload"));
                }

                int clientIndex = rand.nextInt(clientList.size());
                int currencyIndex = rand.nextInt(currencyList.size());
                int price = rand.nextInt(501);
                JSONObject toSend = new JSONObject();
                toSend.put("client_id", clientList.get(clientIndex));
                toSend.put("price", price);
                toSend.put("currencycode", currencyList.get(currencyIndex));
                toSend.put("date", new Timestamp(System.currentTimeMillis()));

                if (rand.nextInt(2) == 1){  // send credit
                    toSend.put("credit", true);
                    creditProducer.send(new ProducerRecord<String, String>(creditTopic, mapper.writeValueAsString(toSend)));
                    System.out.println("Sending to Credit Topic values \n\tClient id: " + clientList.get(clientIndex).get("client_id") + "\n\tValue: " + price + "\n\tCurrency: " + currencyList.get(currencyIndex).get("currencycode"));
                } else {    // send payment
                    toSend.put("credit", false);
                    paymentProducer.send(new ProducerRecord<String, String>(paymentTopic, mapper.writeValueAsString(toSend)));
                    System.out.println("Sending to Payment Topic values \n\tClient id: " + clientList.get(clientIndex).get("client_id") + "\n\tValue: " + price + "\n\tCurrency: " + currencyList.get(currencyIndex).get("currencycode"));
                }

            }
        } finally {
            clientConsumer.close();
            currencyConsumer.close();
            creditProducer.close();
            paymentProducer.close();
        }

    }

}
package KafkaStreams;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import java.util.Properties;

public class Streams {

    public static void main( String[] args ) {

        // assign topicName to string variable
        String creditsTopic = "Credits";
        String paymentsTopic = "Payments";
        String resultsTopic = "Results";
        String dbTopic = "DB Info";

        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafkaStreams");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Long().getClass());

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, Long> creditsLines = builder.stream(creditsTopic);
        KStream<String, Long> paymentsLines = builder.stream(paymentsTopic);

        KTable<String, Long> creditsOutlines = creditsLines.groupByKey().count();
        KTable<String, Long> paymentsOutlines = paymentsLines.groupByKey().count();
        creditsOutlines.toStream().to(resultsTopic);
        creditsOutlines.toStream().to(dbTopic);
        paymentsOutlines.toStream().to(resultsTopic);
        paymentsOutlines.toStream().to(dbTopic);

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        streams.start();

// string.join(dir, esq)
    }
}
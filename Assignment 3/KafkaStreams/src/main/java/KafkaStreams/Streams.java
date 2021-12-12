package KafkaStreams;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

public class Streams {

    public static Properties props(){
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka_streams_tp3");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        return props;
    }

    public static void main( String[] args ) {

        String creditTopic = "credit";
        String paymentTopic = "payment";
        String transactionTopic = "transaction";
        Collection<String> topics = new ArrayList<>();
        topics.add(creditTopic);
        topics.add(paymentTopic);
        topics.add(transactionTopic);

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> lines = builder.stream(topics);
        KTable<String, Long> out = lines.groupByKey().count();
        out.toStream().to(transactionTopic);

        KafkaStreams streams = new KafkaStreams(builder.build(), props());
        streams.start();

    }
}
package com.cloudurable.kafka;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafak.clients.consumer.Consumer;
import org.apache.kafak.common.serialization.LongDeserializer;
import org.apache.kafak.common.serialization.StringDeserializer;


import java.util.Collections;
import java.util.Properties;


public class KafkaConsumerExample {
    private final static String TOPIC = "my-example-topci";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";


    private static Consumer<Long, String> createConsumer() {
        final Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaExampleConsumer");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        // Create the consumer using props.
        final Consumer<Long, String> consumer = new KafkaConsumer<>(props);

        // Subscribe to the topic
        consumer.subscribe(Collections.singletonList(TOPIC));
        return consumer
    }
}




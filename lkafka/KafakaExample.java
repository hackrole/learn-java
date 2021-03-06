package com.cloudurable.kafka;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;

public class KakfaProducerExample {
    private final static String TOPIC = "my-example-topic";
    private final static String BOOTSTRAP_SERVERS = "localhost:9092";

    private static Producer<Long, String> createProducer() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaExampleProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.GetName());
        return new KafkaProducer<>(props);
    }

    static void runProducer(final int sendMessageCount) throws Exception {
        final Producer<Long, String> producer = createProducer();
        long time = System.currentTimeMillis();

        try {
            for (long index = tiem; index < time + sendMessageCount; index++) {
                final ProducerRecord<Long, String> record = new ProducerRecord<>(TOPIC, index, "Hello Mom ", + index);
                RecordMetaData metadata = producer.send(record).get();

                long elapsedTime = System.currentTimeMillis() - time;
                System.out.printf("sent record(key=%s value=%s) " + "meta(partition=%d, offset=%d) time=%d\n",
                                  record.key(), record.value(), metadata.partition(), metadata.offset(), elapsedTime);
            }
        }finally {
            producer.flush();
            producer.close();
        }
    }

    static void runProducer2(final int sendMessageCount) throws InterruptedException {
        final Producer<Long, String> producer = createProducer();
        long time = System.currentTimeMillis();
        final CountDownLatch countDownLatch = new CountDownLatch(sendMessageCount);

        try {
            for (long index = time; index < time + sendMessageCount; index++) {
                final ProducerRecord<Long, String> record = new ProducerRecord<>(TOPIC, index, "Hello Mom" + index);
                producer.send(record, (metadata, exception) -> {
                        long elapsedTime = System.currentTimeMillis() - time;
                        if (metadata != null) {
                            System.out.printf("sent record(key=%s value=%s) " + "meta(partition=%d, offset=%d) time=%d\n",
                                              record.key(), record.value(), metadata.partition(), metadata.offset(), elapsedTime);
                        }else{
                            exception.printStackTrace();
                        }
                        countDownLatch.countDown();
                    });
            }
            countDownLatch.await(25, TimeUnit.SECONDS);
        }finally {
            producer.flush();
            producer.close();
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            runProducer(5);
        }else{
            runProducer(Integer.parseInt(args[0]))
        }
    }
}



package com.producer.producer;

import com.google.gson.Gson;
import com.twitter.hbc.core.Client;
import io.reactivex.Observable;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.Producer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


public class LocalProducer {
    private Client client;
    private BlockingQueue<String> queue;
    private Gson gson;
    private Callback callback;

    public LocalProducer() {
        // Configure auth
//        Authentication authentication = new OAuth1(
//                TwitterConfiguration.CONSUMER_KEY,
//                TwitterConfiguration.CONSUMER_SECRET,
//                TwitterConfiguration.ACCESS_TOKEN,
//                TwitterConfiguration.TOKEN_SECRET);
//
//        // track the terms of your choice. here im only tracking #bigdata.
//        StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
//        endpoint.trackTerms(Collections.singletonList(TwitterConfiguration.HASHTAG));
//
//        queue = new LinkedBlockingQueue<>(10000);
//
//        client = new ClientBuilder()
//                .hosts(Constants.STREAM_HOST)
//                .authentication(authentication)
//                .endpoint(endpoint)
//                .processor(new StringDelimitedProcessor(queue))
//                .build();
//        gson = new Gson();
//        callback = new BasicCallback();
    }

    private Producer<Long, String> getProducer() {
//        Properties properties = new Properties();
//        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfiguration.SERVERS);
//        properties.put(ProducerConfig.ACKS_CONFIG, "1");
//        properties.put(ProducerConfig.LINGER_MS_CONFIG, 500);
//        properties.put(ProducerConfig.RETRIES_CONFIG, 0);
//        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
//        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//
//        return new KafkaProducer<>(properties);
        return null;
    }

    public void run() throws Exception {

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        System.out.println("time t1: " + System.currentTimeMillis());
        DataStream<String> inputStream = env.addSource(new DataSource());
        System.out.println("time t2: " + System.currentTimeMillis());

        //create a producer
        FlinkKafkaProducer<String> producer = createStringProducer();

        inputStream.addSink(producer);

        env.execute("starting...");

    }

    private static class DataSource implements SourceFunction<String> {

        private volatile boolean running = true;

        @Override
        public void run(SourceContext<String> ctx) throws Exception {

            final long startTime = System.currentTimeMillis();

            CountDownLatch latch = new CountDownLatch(1);

            Observable
                    .interval(1, TimeUnit.SECONDS)
                    .subscribe(s -> {
                        System.out.println("time : " + System.currentTimeMillis());
                        ctx.collect("Utish" + s);
                    });

            latch.await();

            final long endTime = System.currentTimeMillis();
            System.out.println("Took " + (endTime - startTime) + " msecs");
        }

        @Override
        public void cancel() {
            running = false;
        }
    }

    public static String generateName(int i) {

        if (i % 2 == 0) {
            return "Utish";
        }

        return "Puppy";
    }


    public static FlinkKafkaProducer<String> createStringProducer() {
        return new FlinkKafkaProducer<String>("localhost:9092", "topic2", new org.apache.flink.api.common.serialization.SimpleStringSchema());
    }


}


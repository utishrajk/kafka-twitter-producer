package com.producer.producer;

import com.producer.config.TwitterConfiguration;
import com.producer.model.Tweet;
import com.producer.model.TweetSerializationSchema;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.endpoint.StatusesSampleEndpoint;
import com.twitter.hbc.core.endpoint.StreamingEndpoint;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.streaming.connectors.twitter.TwitterSource;
import twitter4j.*;

import java.io.Serializable;
import java.util.Arrays;


public class TwitterKafkaProducer {


    public TwitterKafkaProducer() {

    }

    public void run() throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        TwitterSource source = new TwitterSource(TwitterConfiguration.getProperties());
        TweetFilter filter = new TweetFilter();
        source.setCustomEndpointInitializer(filter);

        FlinkKafkaProducer<String> producer = createKafkaProducer("twitterTopic031719");

        DataStream<String> inputStream = env.addSource(source);

        inputStream.addSink(producer);

        inputStream.print();

        env.execute("starting the twitter producer");
    }

    public static FlinkKafkaProducer<String> createKafkaProducer(String topic) {
        return new FlinkKafkaProducer<>("localhost:9092", topic, new SimpleStringSchema());
    }

    public static class TweetFilter implements TwitterSource.EndpointInitializer, Serializable {
        @Override
        public StreamingEndpoint createEndpoint() {
            StatusesFilterEndpoint endpoint = new StatusesFilterEndpoint();
            endpoint.trackTerms(Arrays.asList("nepal"));
            return endpoint;
        }
    }

}

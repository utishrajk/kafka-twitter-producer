package com.producer.config;

import org.apache.flink.streaming.connectors.twitter.TwitterSource;

import java.util.Properties;


public class TwitterConfiguration {
    public static final String CONSUMER_KEY = "";
    public static final String CONSUMER_SECRET = "";
    public static final String ACCESS_TOKEN = "";
    public static final String TOKEN_SECRET = "";
    public static final String HASHTAG = "#nepal";

    public static Properties getProperties() {
        Properties props = new Properties();
        props.setProperty(TwitterSource.CONSUMER_KEY, CONSUMER_KEY);
        props.setProperty(TwitterSource.CONSUMER_SECRET, CONSUMER_SECRET);
        props.setProperty(TwitterSource.TOKEN, ACCESS_TOKEN);
        props.setProperty(TwitterSource.TOKEN_SECRET, TOKEN_SECRET);

        return props;
    }
}

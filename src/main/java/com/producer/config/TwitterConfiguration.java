package com.producer.config;

import org.apache.flink.streaming.connectors.twitter.TwitterSource;

import java.util.Properties;


public class TwitterConfiguration {
    public static final String CONSUMER_KEY = "mQR1GLXJ7S7Pp9AqYdCB0Ad7d";
    public static final String CONSUMER_SECRET = "CYnQSjnEwCJZsDwupnRsd0Fz51Exba1sc1z1FN6VdCJJHpG1Eh";
    public static final String ACCESS_TOKEN = "2315700001-8gND2G2SZt2lushQC559gnygin6vuYaH4GI5bX8";
    public static final String TOKEN_SECRET = "rnUeClLwc4cFOa7G2DAGaTxqelvxp7cb0jXbH2Fcbfhww";
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

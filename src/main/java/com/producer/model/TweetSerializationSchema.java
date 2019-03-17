package com.producer.model;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.flink.api.common.serialization.SerializationSchema;

//Not used right now
public class TweetSerializationSchema implements SerializationSchema<Tweet> {

     static ObjectMapper objectMapper = new com.fasterxml.jackson.databind.ObjectMapper()
            .registerModule(new JavaTimeModule());

    @Override
    public byte[] serialize(Tweet tweet) {
        try {
            return objectMapper.writeValueAsString(tweet).getBytes();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new byte[0];
    }
}

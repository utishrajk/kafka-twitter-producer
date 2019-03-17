package com.producer.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonSerialize
// Not used right now
public class Tweet {

    private long id;
    private String text;
    private String lang;
    private User user;

    @SerializedName("created_at")
    private String createdAt;

}

package com.producer.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@JsonSerialize
@Data
@AllArgsConstructor
//Not used right now
public class User {
    private long id;
    private String name;

    @SerializedName("screen_name")
    private String screenName;
    private String location;

    @SerializedName("followers_count")
    private int followersCount;

}

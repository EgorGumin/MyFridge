package com.lymno.myfridge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @Expose
    @SerializedName("grant_type")
    String grantType;

    @Expose
    @SerializedName("username")
    String username;

    @Expose
    @SerializedName("password")
    String password;

    public User(String grantType, String username, String password) {
        this.grantType = grantType;
        this.username = username;
        this.password = password;
    }
}

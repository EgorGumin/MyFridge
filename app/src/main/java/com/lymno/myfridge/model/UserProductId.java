package com.lymno.myfridge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProductId {

    @Expose
    @SerializedName("UserProductID")
    private int id;

    public int getId() {
        return id;
    }
}

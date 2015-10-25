package com.lymno.myfridge.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Andre on 25.10.2015.
 */
public class UserProductId {

    @SerializedName("UserProductID")
    private int id;

    @SerializedName("ProductID")
    private int baseProductID;

    public int getBaseProductID() {
        return baseProductID;
    }

    public int getId() {
        return id;
    }
}

package com.lymno.myfridge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Colored on 12.02.2016.
 */
public class NewAmount {
    @Expose
    @SerializedName("UserProductID")
    private int userProductID;

    @Expose
    @SerializedName("Amount")
    private int newAmount;

    public NewAmount(int userProductID, int newAmount) {
        this.userProductID = userProductID;
        this.newAmount = newAmount;
    }
}

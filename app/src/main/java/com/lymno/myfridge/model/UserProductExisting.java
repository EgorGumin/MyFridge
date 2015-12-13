package com.lymno.myfridge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Colored on 14.12.2015.
 */
public class UserProductExisting {
    @Expose
    @SerializedName("ProductID")
    private int productID;

    @Expose
    @SerializedName("Amount")
    private int amount;

    @Expose
    @SerializedName("ExpirationDate")
    private String expirationDate;

    public UserProductExisting(int productID, int amount, String expirationDate) {
        this.productID = productID;
        this.amount = amount;
        this.expirationDate = expirationDate;
    }
}

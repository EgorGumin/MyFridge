package com.lymno.myfridge.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Colored on 31.10.2015.
 */
public class NewProductAddResult {
    @SerializedName("UserProductID")
    private int userProductID;
    @SerializedName("ProductID")
    private int productID;

    public int getUserProductID() {
        return userProductID;
    }

    public int getProductID() {
        return productID;
    }
}

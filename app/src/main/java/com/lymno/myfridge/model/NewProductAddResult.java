package com.lymno.myfridge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Colored on 31.10.2015.
 */
public class NewProductAddResult {
    @Expose
    @SerializedName("UserProductID")
    private int userProductID;
    @Expose
    @SerializedName("ProductID")
    private int productID;

    public int getUserProductID() {
        return userProductID;
    }

    public int getProductID() {
        return productID;
    }
}

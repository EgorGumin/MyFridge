package com.lymno.myfridge.model;

import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;
import java.util.Date;

public class UserProduct {
    @SerializedName("UserProductID")
    private int id;
    @SerializedName("FridgeID")
    private int fridgeID;
    @SerializedName("ProductID")
    private int baseProductID;
    @SerializedName("CategoryID")
    private int category;
    @SerializedName("Name")
    private String name;
    @SerializedName("UnitMeasureID")
    private int measure;
    @SerializedName("Amount")
    private int quantity;
    @SerializedName("AmountDefault")
    private int quantityByDefault;
    @SerializedName("ExpirationDate")
    private Date date;


    public UserProduct(int id, int fridgeID, int baseProductID, int category, String name,
                       int measure, int quantity, int quantityByDefault, Date date) {
        this.id = id;
        this.fridgeID = fridgeID;
        this.baseProductID = baseProductID;
        this.category = category;
        this.name = name;
        this.measure = measure;
        this.quantity = quantity;
        this.quantityByDefault = quantityByDefault;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public int getBaseProductID() {
        return baseProductID;
    }

    public int getFridgeID() {
        return fridgeID;
    }

    public int getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getMeasure() {
        return measure;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getQuantityByDefault() {
        return quantityByDefault;
    }

    public Date getDate() {
        return date;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public static class List extends ArrayList<UserProduct> {
    }
}

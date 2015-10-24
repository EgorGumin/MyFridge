package com.lymno.myfridge.model;

import java.sql.Date;
import java.util.ArrayList;

public class UserProduct {
    private int id;
    private int baseProductID;
    private int category;
    private String name;
    private int measure;
    private int quantity;
    private int quantityByDefault;
    private Date date;


    public UserProduct(int id, int baseProductID, int category, String name, int measure, int quantity,
                       int quantityByDefault, Date date) {
        this.id = id;
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

    @SuppressWarnings("serial")
    public static class List extends ArrayList<UserProduct> {
    }
}

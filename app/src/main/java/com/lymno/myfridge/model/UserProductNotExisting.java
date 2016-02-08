package com.lymno.myfridge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProductNotExisting {
    @Expose
    @SerializedName("Barcode")
    private String barcode;

    @Expose
    @SerializedName("Name")
    private String name;

    @Expose
    @SerializedName("CategoryName")
    private String category;

    @Expose
    @SerializedName("Amount")
    private int amount;

    @Expose
    @SerializedName("AmountDefault")
    private int amountDefault;

    //    может вызвать падение
    @Expose
    @SerializedName("UnitMeasureID")
    private String measureID;

    @Expose
    @SerializedName("ExpirationDate")
    private String date;

    public UserProductNotExisting(String barcode, String name, String category, int amount, int amountDefault, String measureID, String date) {
        this.barcode = barcode;
        this.name = name;
        this.category = category;
        this.amount = amount;
        this.amountDefault = amountDefault;
        this.measureID = measureID;
        this.date = date;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getAmount() {
        return amount;
    }

    public int getAmountDefault() {
        return amountDefault;
    }

    public String getMeasureID() {
        return measureID;
    }

    public String getDate() {
        return date;
    }
}

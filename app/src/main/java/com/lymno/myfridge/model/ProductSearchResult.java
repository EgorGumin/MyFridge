package com.lymno.myfridge.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Egor on 24.10.2015.
 */
public class ProductSearchResult {
    @SerializedName("ProductID")
    private int productID;
    @SerializedName("CategoryID")
    private int categoryID;
    @SerializedName("Name")
    private String name;
    @SerializedName("AmountDefault")
    private int amountDefault;
    @SerializedName("UnitMeasureID")
    private int unitMeasureID;

    public int getProductID() {
        return productID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public String getName() {
        return name;
    }

    public int getAmountDefault() {
        return amountDefault;
    }

    public int getUnitMeasureID() {
        return unitMeasureID;
    }

}

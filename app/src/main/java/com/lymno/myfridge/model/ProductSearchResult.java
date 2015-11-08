package com.lymno.myfridge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Egor on 24.10.2015.
 */
public class ProductSearchResult {
    @Expose
    @SerializedName("ProductID")
    private int productID;
    @Expose
    @SerializedName("CategoryID")
    private int categoryID;

    @Expose
    @SerializedName("Name")
    private String name;

    @Expose
    @SerializedName("AmountDefault")
    private int amountDefault;

    @Expose
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

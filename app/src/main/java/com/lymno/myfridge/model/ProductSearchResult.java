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
    @SerializedName("CategoryName")
    private String categoryName;

    @Expose
    @SerializedName("Name")
    private String name;

    @Expose
    @SerializedName("AmountDefault")
    private int amountDefault;

    //    может вызвать ошибку
    @Expose
    @SerializedName("UnitMeasureID")
    private String unitMeasureID;

    public int getProductID() {
        return productID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getName() {
        return name;
    }

    public int getAmountDefault() {
        return amountDefault;
    }

    public String getUnitMeasureID() {
        return unitMeasureID;
    }

}

package com.lymno.myfridge.network.request;

import com.lymno.myfridge.model.UserProductId;
import com.lymno.myfridge.network.Essen;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import retrofit.http.Query;
import roboguice.util.temp.Ln;

public class NewPushProd extends RetrofitSpiceRequest<UserProductId, Essen> {

    String userId;
    String barCode;
    String categoryId;
    String name;
    String AmountDef;
    String UtilMeasuredId;
    String Amount;
    String ExpirationDate;

    public NewPushProd(String barCode
            , String categoryId
            , String name
            , String AmountDef
            , String UtilMeasuredId
            , String Amount
            , String ExpirationDate) {
        super(UserProductId.class, Essen.class);

        this.userId = "4";
        this.barCode = barCode;
        this.categoryId = categoryId;
        this.name = name;
        this.AmountDef = AmountDef;
        this.UtilMeasuredId = UtilMeasuredId;
        this.Amount = Amount;
        this.ExpirationDate = ExpirationDate;

    }


    @Override
    public UserProductId loadDataFromNetwork() {
        Ln.d("Call web service ");
        return getService().addNewProduct(userId,
                barCode
                , categoryId
                , name
                , AmountDef
                , UtilMeasuredId
                , Amount
                , ExpirationDate);
    }

    public String getUserId() {
        return userId;
    }

    public String getBarCode() {
        return barCode;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public String getAmountDef() {
        return AmountDef;
    }

    public String getUtilMeasuredId() {
        return UtilMeasuredId;
    }

    public String getAmount() {
        return Amount;
    }

    public String getExpirationDate() {
        return ExpirationDate;
    }

}

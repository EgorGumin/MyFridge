package com.lymno.myfridge.network.request;

import com.lymno.myfridge.model.NewProductAddResult;
import com.lymno.myfridge.model.UserProductId;
import com.lymno.myfridge.network.Essen;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import retrofit.http.Query;
import roboguice.util.temp.Ln;

public class NewPushProd extends RetrofitSpiceRequest<NewProductAddResult, Essen> {

    String userId;
    String barCode;
    int categoryId;
    int fridgeID;
    String name;
    int AmountDef;
    int UtilMeasuredId;
    int Amount;
    String ExpirationDate;

    public NewPushProd(String barCode, int categoryId, String name, int AmountDef,
                       int UtilMeasuredId, int Amount, String ExpirationDate) {
        super(NewProductAddResult.class, Essen.class);

        this.userId = "4";
        this.fridgeID = 1;
        this.barCode = barCode;
        this.categoryId = categoryId;
        this.name = name;
        this.AmountDef = AmountDef;
        this.UtilMeasuredId = UtilMeasuredId;
        this.Amount = Amount;
        this.ExpirationDate = ExpirationDate;

    }


    @Override
    public NewProductAddResult loadDataFromNetwork() {
        Ln.d("Call web service ");
        return getService().addNewProduct(userId, fridgeID, barCode, categoryId, name,
                AmountDef, UtilMeasuredId, Amount, ExpirationDate);
    }

    public String getUserId() {
        return userId;
    }

    public String getBarCode() {
        return barCode;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public int getAmountDef() {
        return AmountDef;
    }

    public int getUtilMeasuredId() {
        return UtilMeasuredId;
    }

    public int getAmount() {
        return Amount;
    }

    public String getExpirationDate() {
        return ExpirationDate;
    }

}

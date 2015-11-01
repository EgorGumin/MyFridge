package com.lymno.myfridge.network.request;

import com.lymno.myfridge.model.ResultChange;
import com.lymno.myfridge.network.Api;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import roboguice.util.temp.Ln;

/**
 * Created by Andre on 25.10.2015.
 */
public class ChangeRequest extends RetrofitSpiceRequest<ResultChange, Api> {

    String UserProductID;
    String Amount;

    public ChangeRequest(  String UserProductID,String Amount){
        super(ResultChange.class,Api.class);

        this.UserProductID=UserProductID;
        this.Amount=Amount;

    }
    @Override
    public ResultChange loadDataFromNetwork() {
        Ln.d("Call web service ");
        return getService().change(UserProductID,Amount);
    }
}

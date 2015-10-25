package com.lymno.myfridge.network.request;

import com.lymno.myfridge.model.ProductSearchResult;
import com.lymno.myfridge.model.ResultChange;
import com.lymno.myfridge.network.Essen;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import retrofit.http.Query;
import roboguice.util.temp.Ln;

/**
 * Created by Andre on 25.10.2015.
 */
public class ChangeRequest extends RetrofitSpiceRequest<ResultChange, Essen> {

    String UserProductID;
    String Amount;

    public ChangeRequest(  String UserProductID,String Amount){
        super(ResultChange.class,Essen.class);

        this.UserProductID=UserProductID;
        this.Amount=Amount;

    }
    @Override
    public ResultChange loadDataFromNetwork() {
        Ln.d("Call web service ");
        return getService().change(UserProductID,Amount);
    }
}

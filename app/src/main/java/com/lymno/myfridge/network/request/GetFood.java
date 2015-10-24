package com.lymno.myfridge.network.request;

import com.lymno.myfridge.model.UserProduct;
import com.lymno.myfridge.network.Essen;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import roboguice.util.temp.Ln;

public class GetFood extends RetrofitSpiceRequest<UserProduct.List, Essen> {

    public GetFood() {
        super(UserProduct.List.class, Essen.class);
    }

    @Override
    public UserProduct.List loadDataFromNetwork() {
        Ln.d("Call web service ");

        return getService().foodList();
    }
}

package com.lymno.myfridge.network.request;

import com.lymno.myfridge.model.UserProduct;
import com.lymno.myfridge.network.Essen;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import roboguice.util.temp.Ln;
/*
public class TestRequest extends RetrofitSpiceRequest<UserProduct, Essen> {
    private String code;

    public TestRequest(String code) {
        super(UserProduct.class, Essen.class);
        this.code = code;
    }

    @Override
    public UserProduct loadDataFromNetwork() {
        Ln.d("Call web service ");
        return getService().food(code);
    }

}
*/
package com.lymno.myfridge.network.request;

import com.lymno.myfridge.model.Food;
import com.lymno.myfridge.network.Essen;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import roboguice.util.temp.Ln;

public class TestRequest extends RetrofitSpiceRequest<Food, Essen> {
    private String code;

    public TestRequest(String code) {
        super(Food.class, Essen.class);
        this.code = code;
    }

    @Override
    public Food loadDataFromNetwork() {
        Ln.d("Call web service ");
        return getService().food(code);
    }
}

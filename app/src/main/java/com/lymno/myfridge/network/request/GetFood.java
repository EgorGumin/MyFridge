package com.lymno.myfridge.network.request;

import com.lymno.myfridge.network.Essen;
import com.lymno.myfridge.model.Food;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import roboguice.util.temp.Ln;

public class GetFood extends RetrofitSpiceRequest<Food.List, Essen> {

    public GetFood() {
        super(Food.List.class, Essen.class);
    }

    @Override
    public Food.List loadDataFromNetwork() {
        Ln.d("Call web service ");

        return getService().foodList();
    }
}

package com.lymno.myfridge.network.request;

import com.lymno.myfridge.model.UserProduct;
import com.lymno.myfridge.network.Essen;
import com.lymno.myfridge.network.SampleRetrofitSpiceService;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import roboguice.util.temp.Ln;

/**
 * Created by Egor on 24.10.2015.
 */
public class SyncProducts extends RetrofitSpiceRequest<UserProduct.List, Essen> {


    public SyncProducts() {
        super(UserProduct.List.class, Essen.class);
    }

    @Override
    public UserProduct.List loadDataFromNetwork() {
        Ln.d("Call web service ");

        return getService().syncProducts(SampleRetrofitSpiceService.getUserId());
    }
}

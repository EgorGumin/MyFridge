package com.lymno.myfridge.network;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

public class SampleRetrofitSpiceService extends RetrofitGsonSpiceService {

    private final static String BASE_URL = "http://essen.azurewebsites.net/";

    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(Essen.class);
    }

    @Override
    protected String getServerUrl() {
        return BASE_URL;
    }
}

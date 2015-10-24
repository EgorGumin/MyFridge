package com.lymno.myfridge.network;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

import retrofit.RestAdapter;

public class SampleRetrofitSpiceService extends RetrofitGsonSpiceService {

    private final static String BASE_URL = "http://s123s.azurewebsites.net/";

    public static String getSessionId() {
        return SESSION_ID;
    }

    private final static String SESSION_ID = "777";

    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(Essen.class);
    }

    @Override
    protected String getServerUrl() {
        return BASE_URL;
    }

    //????????????
    @Override
    protected RestAdapter.Builder createRestAdapterBuilder() {
        return new RestAdapter.Builder().setEndpoint(getServerUrl()).setConverter(getConverter()).setClient(new InterceptingOkClient());
    }
}

package com.lymno.myfridge.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

import retrofit.RestAdapter;
import retrofit.converter.Converter;
import retrofit.converter.GsonConverter;

public class SampleRetrofitSpiceService extends RetrofitGsonSpiceService {

    private final static String BASE_URL = "http://s123s.azurewebsites.net/";
    private final static String USER_ID = "4";
    private final static String SESSION_ID = "777";

    @Override
    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(Api.class);
    }

    @Override
    protected String getServerUrl() {
        return BASE_URL;
    }

    public static String getUserId() {
        return USER_ID;
    }

    public static String getSessionId() {
        return SESSION_ID;
    }

    //????????????
    @Override
    protected RestAdapter.Builder createRestAdapterBuilder() {
        return new RestAdapter.Builder()
                .setEndpoint(getServerUrl())
                .setConverter(getConverter())
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new InterceptingOkClient());
    }

    @Override
    protected Converter createConverter() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyyMMdd")
                .create();
        return  new GsonConverter(gson);
    }
}

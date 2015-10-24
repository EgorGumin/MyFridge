package com.lymno.myfridge.network;

import com.lymno.myfridge.model.UserProduct;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface Essen {
    @GET("/getFood")
    UserProduct.List foodList();

    @GET("/test/ine")
    UserProduct food(@Query("id") String code);
}

package com.lymno.myfridge.network;

import com.lymno.myfridge.model.Food;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface Essen {
    @GET("/getFood")
    Food.List foodList();

    @GET("/test/ine")
    Food food(@Query("id") String code);
}

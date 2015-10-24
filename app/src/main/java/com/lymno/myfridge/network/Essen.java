package com.lymno.myfridge.network;

import com.lymno.myfridge.model.ProductSearchResult;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface Essen {

    //?????
    @GET("/products/ProductSearch")
    ProductSearchResult searchBarcode(@Query("barcode") String code, @Query("SessionID") String sessionID);
}

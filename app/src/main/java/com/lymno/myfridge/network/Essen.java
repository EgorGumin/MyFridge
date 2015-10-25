package com.lymno.myfridge.network;

import com.lymno.myfridge.model.ProductSearchResult;
import com.lymno.myfridge.model.UserProduct;
import com.lymno.myfridge.model.UserProductId;

import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface Essen {

    //?????
    @GET("/products/ProductSearch")
    ProductSearchResult searchBarcode(@Query("barcode") String code, @Query("SessionID") String sessionID);

    @GET("/products/GetUserProducts")
    UserProduct.List syncProducts(@Query("id") String id);

    @GET("/products/ProductAdd")
    UserProductId addProd(@Query("ProductID") String prodId,@Query("Amount") String amount,@Query("ExspirationDate") String expDate,
                        @Query("UserId") String userId);
}

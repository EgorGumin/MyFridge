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

    @GET("/products/NewProductAdd")
    UserProductId addNewProduct(@Query("UserId") String userId
                                ,@Query("BarCode") String barCode
                                ,@Query("CategoryId") String categoryId
                                ,@Query("Name") String name
                                ,@Query("AmountDefault") String AmountDef
                                ,@Query("UnitMeasuredId") String UtilMeasuredId
                                ,@Query("Amount") String Amount
                                ,@Query("ExpirationDate") String ExpirationDate);
}

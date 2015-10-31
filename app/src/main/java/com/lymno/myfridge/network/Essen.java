package com.lymno.myfridge.network;

import com.lymno.myfridge.model.NewProductAddResult;
import com.lymno.myfridge.model.ProductSearchResult;
import com.lymno.myfridge.model.ResultChange;
import com.lymno.myfridge.model.Recipe;
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
    UserProductId addProd(@Query("ProductID") int prodId, @Query("Amount") int amount, @Query("ExspirationDate") String expDate,
                          @Query("UserId") String userId);

    @GET("/recipes/GetRecipesSimple")
    Recipe.List getRecipesSimple(@Query("idUser") String idUser);

    @GET("/products/NewProductAdd")
    NewProductAddResult addNewProduct(@Query("UserId") String userId
            , @Query("FridgeID") int fridgeID
            , @Query("Barcode") String barCode
            , @Query("CategoryID") int categoryId
            , @Query("Name") String name
            , @Query("AmountDefault") int AmountDef
            , @Query("UnitMeasureID") int UtilMeasuredId
            , @Query("Amount") int Amount
            , @Query("ExpirationDate") String ExpirationDate);


    @GET("/products/AmountChange")
    ResultChange change(@Query("UserProductID") String UserProductID,
                        @Query("Amount") String Amount);


}

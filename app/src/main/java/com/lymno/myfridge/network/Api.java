package com.lymno.myfridge.network;

import com.lymno.myfridge.model.Category;
import com.lymno.myfridge.model.NewProductAddResult;
import com.lymno.myfridge.model.ProductSearchResult;
import com.lymno.myfridge.model.Recipe;
import com.lymno.myfridge.model.ResultChange;
import com.lymno.myfridge.model.Token;
import com.lymno.myfridge.model.User;
import com.lymno.myfridge.model.UserProduct;
import com.lymno.myfridge.model.UserProductId;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Query;

public interface Api {
    //Получить токен по логину и паролю (вход)
    @POST("/api/account/getToken")
    void auth(@Body User user, Callback<Token> cb);

    //Получить продукты пользователя
    @GET("/api/userproduct/GetUserProducts")
    void syncProducts(@Header("Authorization") String token, Callback<ArrayList<UserProduct>> callback);

    //Получить рецепты, строго подходящие пользователю
    @GET("/api/recipes/getSimpleRecipes")
    void getRecipesSimple(@Header("Authorization") String token, Callback<ArrayList<Recipe>> callback);

    //ok
    @GET("/products/ProductSearch")
    void searchBarcode(@Query("barcode") String code, @Query("SessionID") String sessionID, Callback<ProductSearchResult> callback);


    //ok
    @GET("/products/ProductAdd")
    void addProd(@Query("ProductID") int prodId, @Query("Amount") int amount, @Query("ExspirationDate") String expDate,
                 @Query("UserId") String userId, Callback<UserProductId> callback);


    @GET("api/sync/GetCategories")
    void syncCategories(@Header("Authorization") String token, Callback<ArrayList<Category>> callback);


    @GET("/products/NewProductAdd")
    void addNewProduct(@Query("UserId") String userId, @Query("FridgeID") int fridgeID,
                       @Query("Barcode") String barCode, @Query("CategoryID") int categoryId,
                       @Query("Name") String name, @Query("AmountDefault") int AmountDef,
                       @Query("UnitMeasureID") int UtilMeasuredId, @Query("Amount") int Amount,
                       @Query("ExpirationDate") String ExpirationDate, Callback<NewProductAddResult> callback);

    //пока не будет обновляться, ибо не нужен
    @GET("/products/AmountChange")
    ResultChange change(@Query("UserProductID") String UserProductID,
                        @Query("Amount") String Amount);
}

package com.lymno.myfridge.network;

import com.lymno.myfridge.model.Category;
import com.lymno.myfridge.model.NewProductAddResult;
import com.lymno.myfridge.model.ProductSearchResult;
import com.lymno.myfridge.model.Recipe;
import com.lymno.myfridge.model.ResultChange;
import com.lymno.myfridge.model.Token;
import com.lymno.myfridge.model.User;
import com.lymno.myfridge.model.UserProduct;
import com.lymno.myfridge.model.UserProductExisting;
import com.lymno.myfridge.model.UserProductId;
import com.lymno.myfridge.model.UserProductNotExisting;

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
    @GET("/api/userproduct/getUserProducts")
    void syncProducts(@Header("Authorization") String token, Callback<ArrayList<UserProduct>> callback);

    //Получить рецепты, строго подходящие пользователю
    @GET("/api/recipes/getSimpleRecipes")
    void getRecipesSimple(@Header("Authorization") String token, Callback<ArrayList<Recipe>> callback);

    //Проверить, если продукт с таким штрих-кодом в базе
    @GET("/api/userproduct/productSearchByBarcode")
    void searchBarcode(@Header("Authorization") String token, @Query("barcode") String code, Callback<ProductSearchResult> callback);


    //Добавляет продукт, если такой уже есть в базе на сервере
    @POST("/api/userproduct/addProduct")
    void addProd(@Header("Authorization") String token, @Body UserProductExisting product, Callback<UserProductId> callback);

    //Синхронизирует список категорий, который используется для автокомплита при добавлении продукта
    @GET("/api/sync/GetCategories")
    void syncCategories(Callback<ArrayList<Category>> callback);


    @POST("/api/userproduct/addNewCategoryProduct")
    void addNewProduct(@Header("Authorization") String token, @Body UserProductNotExisting product, Callback<NewProductAddResult> callback);

    //пока не будет обновляться, ибо не нужен
    @GET("/products/AmountChange")
    ResultChange change(@Query("UserProductID") String UserProductID,
                        @Query("Amount") String Amount);
}

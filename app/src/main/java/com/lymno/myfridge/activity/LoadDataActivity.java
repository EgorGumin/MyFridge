package com.lymno.myfridge.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lymno.myfridge.R;
import com.lymno.myfridge.model.Category;
import com.lymno.myfridge.network.RestClient;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LoadDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_data);
        RestClient.get().syncCategories(RestClient.getUserId(), new Callback<ArrayList<Category>>() {
            @Override
            public void success(ArrayList<Category> categories, Response response) {
                Category.recreate(categories);
                Intent intent = new Intent(LoadDataActivity.this, LoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }
}

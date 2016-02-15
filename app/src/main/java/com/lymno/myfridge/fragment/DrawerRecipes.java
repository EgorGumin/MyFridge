package com.lymno.myfridge.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lymno.myfridge.R;
import com.lymno.myfridge.adapter.RecipeAdapter;
import com.lymno.myfridge.model.Recipe;
import com.lymno.myfridge.network.Api;
import com.lymno.myfridge.network.RestClient;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DrawerRecipes extends Fragment {
    @Bind(R.id.recipes_swipe_refresh_layout)
    protected SwipeRefreshLayout refreshLayout;

    private SharedPreferences settings;
    private String tokenKey = "com.lymno.myfridge.activity.token";
    private String token;
    private final Api api = RestClient.get();

    @Bind(R.id.recipes_list_recycler_list)
    protected RecyclerView recyclerView;



    public DrawerRecipes() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        ArrayList<Recipe> recipes = Recipe.getAll();
        if (recipes != null) {
            RecipeAdapter recipeAdapter = new RecipeAdapter(recipes);
            recyclerView.setAdapter(recipeAdapter);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drawer_recipes, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        settings = getActivity().getSharedPreferences("com.lymno.myfridge.activity", Context.MODE_PRIVATE);
        token = settings.getString(tokenKey, "");
        refreshLayout.setColorSchemeColors(R.color.primary);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                api.getRecipesSimple(token, new Callback<ArrayList<Recipe>>() {
                    @Override
                    public void success(ArrayList<Recipe> recipes, Response response) {
                        refreshLayout.setRefreshing(false);
                        if (recipes != null) {
                            RecipeAdapter newAdapter = new RecipeAdapter(recipes);

                            Recipe.recreate(recipes);
                            recyclerView.setAdapter(newAdapter);
                        } else {
                            Toast.makeText(getActivity(), "Неправильный тип кода или такого продукта еще нет в базе.", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        refreshLayout.setRefreshing(false);
                        Toast.makeText(getActivity(), "Failure: " + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return view;
    }

}

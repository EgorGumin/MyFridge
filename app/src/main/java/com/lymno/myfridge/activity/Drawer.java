package com.lymno.myfridge.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.lymno.myfridge.R;
import com.lymno.myfridge.fragment.DrawerProducts;
import com.lymno.myfridge.fragment.DrawerRecipes;
import com.lymno.myfridge.model.Category;
import com.lymno.myfridge.model.Ingredient;
import com.lymno.myfridge.model.MyModel;
import com.lymno.myfridge.model.Recipe;
import com.lymno.myfridge.model.UserProduct;
import com.lymno.myfridge.network.RestClient;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerProducts drawerProductsFragment;
    private DrawerRecipes drawerRecipesFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        drawerProductsFragment = new DrawerProducts();
        drawerRecipesFragment = new DrawerRecipes();

        //TODO: 14.02.2016 найти способ поумнее поставить дефолтный фрагмент
        navigationView.setCheckedItem(R.id.drawer_menu_products);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.drawer_fragments_container, drawerProductsFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (id == R.id.drawer_menu_products) {
            fragmentTransaction.replace(R.id.drawer_fragments_container, drawerProductsFragment);
            fragmentTransaction.commit();
        } else if (id == R.id.drawer_menu_recipes) {
            fragmentTransaction.replace(R.id.drawer_fragments_container, drawerRecipesFragment);
            fragmentTransaction.commit();
        } else if (id == R.id.drawer_menu_refresh_categories) {
            RestClient.get().syncCategories(new Callback<ArrayList<Category>>() {
                @Override
                public void success(ArrayList<Category> categories, Response response) {
                    Category.recreate(categories);
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(Drawer.this, error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            });

        } else if (id == R.id.drawer_menu_log_out) {
//          Удаляем сохраненный токен
            String tokenKey = "com.lymno.myfridge.activity.token";
            SharedPreferences settings = this.getSharedPreferences("com.lymno.myfridge.activity",
                    Context.MODE_PRIVATE);
            settings.edit().putString(tokenKey, "").apply();
//          Очищаем БД
            MyModel.truncate(Recipe.class);
            MyModel.truncate(Ingredient.class);
            MyModel.truncate(UserProduct.class);

            Intent intent = new Intent(Drawer.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

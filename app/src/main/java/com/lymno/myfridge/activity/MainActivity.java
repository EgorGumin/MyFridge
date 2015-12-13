package com.lymno.myfridge.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.lymno.myfridge.R;
import com.lymno.myfridge.adapter.FoodAdapter;
import com.lymno.myfridge.adapter.RecipeAdapter;
import com.lymno.myfridge.barcode_scanner.ScannerFragmentActivity;
import com.lymno.myfridge.model.Recipe;
import com.lymno.myfridge.model.UserProduct;
import com.lymno.myfridge.network.Api;
import com.lymno.myfridge.network.RestClient;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    private static final int MY_FOOD = 1;
    private static final int MY_RECIPES = 2;
    private static final int LOG_OUT = 30;

    private AccountHeader headerResult = null;
    private Drawer result = null;

    private FoodAdapter mFoodAdapter;
    private RecipeAdapter mRecipeAdapter;
    private SwipeRefreshLayout refreshLayout;
    public RecyclerView recyclerView;

    @Bind(R.id.myFAB)
    FloatingActionButton myFAB;

    SharedPreferences settings;
    String tokenKey = "com.lymno.myfridge.activity.token";
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set up rest api
        final Api api = RestClient.get();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        settings = this.getSharedPreferences(
                "com.lymno.myfridge.activity", Context.MODE_PRIVATE);
        token = settings.getString(tokenKey, "");
        if (token.isEmpty()) {
            Toast.makeText(MainActivity.this, "Token is empty!", Toast.LENGTH_LONG).show();
        }

        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        refreshLayout.setColorSchemeColors(R.color.primary);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (result.getCurrentSelectedPosition() == MY_FOOD) {
                    showFAB(true);
                    api.syncProducts(token, new Callback<ArrayList<UserProduct>>() {
                        @Override
                        public void success(ArrayList<UserProduct> userProducts, Response response) {
                            refreshLayout.setRefreshing(false);
//                          Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                            if (userProducts != null) {
                                UserProduct.recreate(userProducts);
                                FoodAdapter newAdapter = new FoodAdapter(userProducts);
                                recyclerView.setAdapter(newAdapter);
                            } else {
                                Toast.makeText(MainActivity.this, "Неправильный тип кода или такого продукта еще нет в базе.", Toast.LENGTH_LONG).show();
                            }
                        }
                        @Override
                        public void failure(RetrofitError error) {
                            refreshLayout.setRefreshing(false);
                        }
                    });
                }
                if (result.getCurrentSelectedPosition() == MY_RECIPES) {
                    api.getRecipesSimple(token, new Callback<ArrayList<Recipe>>() {
                        @Override
                        public void success(ArrayList<Recipe> recipes, Response response) {
                            refreshLayout.setRefreshing(false);
                            //Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                            if (recipes != null) {
                                RecipeAdapter newAdapter = new RecipeAdapter(recipes);
                                Recipe.recreate(recipes);
                                recyclerView.setAdapter(newAdapter);
                            } else {
                                Toast.makeText(MainActivity.this, "Неправильный тип кода или такого продукта еще нет в базе.", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            refreshLayout.setRefreshing(false);
                            Toast.makeText(MainActivity.this, "Failure: " + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    showFAB(false);
                }

            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.food_list_recycler_list);

        // Управление Floating Action Button
        myFAB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScannerFragmentActivity.class);
                startActivity(intent);
            }
        });

        // Handle Toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.login_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("title");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));

        // Добавим профиль пользователя
        final IProfile profile = new ProfileDrawerItem().withName("Mike Penz").withEmail("mikepenz@gmail.com").withIcon(ContextCompat.getDrawable(MainActivity.this, R.drawable.profile));

        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(true)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        profile,
                        //don't ask but google uses 14dp for the add account icon in gmail but 20dp for the normal icons (like manage account)
                        new ProfileSettingDrawerItem().withName("Add Account").withDescription("Add new GitHub Account")
                                .withIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_add).actionBarSize().paddingDp(5)),
                        new ProfileSettingDrawerItem().withName("Manage Account").withIcon(GoogleMaterial.Icon.gmd_settings),
                        new ProfileSettingDrawerItem().withName("Выйти из аккаунта").withIcon(GoogleMaterial.Icon.gmd_settings).withIdentifier(LOG_OUT)
                )
                .withOnAccountHeaderSelectionViewClickListener(new AccountHeader.OnAccountHeaderSelectionViewClickListener() {
                    @Override
                    public boolean onClick(View view, IProfile profile) {
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .withToolbar(toolbar)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(false)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_food).withIcon(FontAwesome.Icon.faw_cutlery).withIdentifier(MY_FOOD),//faw_spoon
                        new PrimaryDrawerItem().withName(R.string.drawer_item_recipes).withIcon(FontAwesome.Icon.faw_book).withIdentifier(MY_RECIPES),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_recommended_recipes).withIcon(FontAwesome.Icon.faw_commenting).withEnabled(false),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_menu).withIcon(FontAwesome.Icon.faw_calendar).withEnabled(false),

                        new SectionDrawerItem(),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_settings).withIcon(FontAwesome.Icon.faw_cog).withEnabled(false),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_help).withIcon(FontAwesome.Icon.faw_question).withEnabled(false),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_contact).withIcon(FontAwesome.Icon.faw_bullhorn).withEnabled(false),
                        new SectionDrawerItem().withName(R.string.app_version),
                        new SecondaryDrawerItem().withName("Выйти из аккаунта").withIcon(GoogleMaterial.Icon.gmd_settings).withIdentifier(LOG_OUT)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        if (drawerItem != null && drawerItem.getIdentifier() == MY_FOOD) {
                            ArrayList<UserProduct> userProducts = UserProduct.getAll();
                            if (userProducts != null) {
                                FoodAdapter newAdapter = new FoodAdapter(userProducts);
                                recyclerView.setAdapter(newAdapter);
                            }
//                            FoodAdapter newAdapter = new FoodAdapter(UserProductsDatabase.getUserProducts());
//                            recyclerView.setAdapter(newAdapter);
                        }
                        if (drawerItem != null && drawerItem.getIdentifier() == MY_RECIPES) {
                            ArrayList<Recipe> recipes = Recipe.getAll();
                            if (recipes != null) {
                                RecipeAdapter newAdapter = new RecipeAdapter(recipes);
                                recyclerView.setAdapter(newAdapter);
                            }


                            //getSpiceManager().execute(new GetRecipesSimple(), "getR", DurationInMillis.ONE_MINUTE, new RecipesUpdateListener());
                        }
                        if (drawerItem != null && drawerItem.getIdentifier() == LOG_OUT) {
                            String tokenKey = "com.lymno.myfridge.activity.token";
                            settings.edit().putString(tokenKey, "").apply();
                            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }

                        if (drawerItem instanceof Nameable) {
                            toolbar.setTitle(((Nameable) drawerItem).getName().getText(MainActivity.this));
                        }
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

        result.setSelection(MY_FOOD, true);
        recyclerView = (RecyclerView) findViewById(R.id.food_list_recycler_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        //mFoodAdapter = new FoodAdapter(UserProductsDatabase.getUserProducts());
        ArrayList<UserProduct> userProducts = UserProduct.getAll();
        if (userProducts != null) {
            mFoodAdapter = new FoodAdapter(userProducts);
            recyclerView.setAdapter(mFoodAdapter);
        }
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        //handle the back press close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (result.getCurrentSelectedPosition() == MY_FOOD) {
            //TODO переделать onResume
//            mFoodAdapter = new FoodAdapter(UserProductsDatabase.getUserProducts());
//            recyclerView.setAdapter(mFoodAdapter);
        }
    }

    protected void showFAB(boolean show) {
        if (myFAB.isShown() && !show) {
            myFAB.hide();
        }
        if (!myFAB.isShown() && show) {
            myFAB.show();
        }
    }
}

package com.lymno.myfridge.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.lymno.myfridge.adapter.FoodAdapter;
import com.lymno.myfridge.adapter.RecipeAdapter;
import com.lymno.myfridge.barcode_scanner.ScannerFragmentActivity;
import com.lymno.myfridge.Examples;
import com.lymno.myfridge.R;
import com.lymno.myfridge.Recipe;
import com.lymno.myfridge.database.DBHelper;
import com.lymno.myfridge.database.UserProductsDatabase;
import com.lymno.myfridge.model.UserProduct;
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

import java.sql.Date;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int MY_FOOD = 1;
    private static final int MY_RECIPES = 2;

    private AccountHeader headerResult = null;
    private Drawer result = null;

//    private UserProductsDatabase db = new UserProductsDatabase();

    private FoodAdapter mAdapter;
    Context context;
    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new DBHelper(context);

        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.food_list_recycler_list);

        // Управление Floating Action Button
        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.myFAB);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScannerFragmentActivity.class);
                startActivity(intent);
            }
        });

        // Handle Toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("title");

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
                        new ProfileSettingDrawerItem().withName("Add Account").withDescription("Add new GitHub Account").withIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_add).actionBarSize().paddingDp(5)).withIdentifier(1),
                        new ProfileSettingDrawerItem().withName("Manage Account").withIcon(GoogleMaterial.Icon.gmd_settings)
                )
                .withSavedInstance(savedInstanceState)
                .build();

        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(this)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_food).withIcon(FontAwesome.Icon.faw_cutlery).withIdentifier(MY_FOOD),//faw_spoon
                        new PrimaryDrawerItem().withName(R.string.drawer_item_recipes).withIcon(FontAwesome.Icon.faw_book).withIdentifier(MY_RECIPES),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_recommended_recipes).withIcon(FontAwesome.Icon.faw_commenting).withEnabled(false),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_menu).withIcon(FontAwesome.Icon.faw_calendar).withEnabled(false),
                        //отладка бд
                        new PrimaryDrawerItem().withName("Добавить в базу").withIdentifier(3),
                        new PrimaryDrawerItem().withName("Прочитать из базы").withIdentifier(4),

                        new SectionDrawerItem(),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_settings).withIcon(FontAwesome.Icon.faw_cog).withEnabled(false),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_help).withIcon(FontAwesome.Icon.faw_question).withEnabled(false),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_contact).withIcon(FontAwesome.Icon.faw_bullhorn).withEnabled(false),
                        new SectionDrawerItem().withName(R.string.app_version)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {

                        if (drawerItem != null && drawerItem.getIdentifier() == MY_FOOD) {
                            ArrayList<UserProduct> foodList = Examples.getAllFood();
                            foodList.addAll(Examples.getAllFood()); // для количества
                            FoodAdapter mAdapter = new FoodAdapter(foodList);
                            recyclerView.setAdapter(mAdapter);
                        }
                        if (drawerItem != null && drawerItem.getIdentifier() == MY_RECIPES) {
                            ArrayList<Recipe> recipesList = Examples.getAllRecipes();
                            RecipeAdapter newAdapter = new RecipeAdapter(recipesList);
                            recyclerView.setAdapter(newAdapter);
                        }

                        if (drawerItem != null && drawerItem.getIdentifier() == 3) {
                            //добавим в базу
                            UserProduct test = new UserProduct(1,1,1,"Prod name", 1, 1,1, Date.valueOf("12-12-2013"));
                            UserProductsDatabase.addUserProduct(test);
                        }

                        if (drawerItem != null && drawerItem.getIdentifier() == 4) {
                            //добавим в базу
                            ArrayList<UserProduct> list = UserProductsDatabase.getUserProducts();
                            UserProduct up = list.get(0);
                            Toast.makeText(MainActivity.this, up.getName(), Toast.LENGTH_LONG).show();
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
        ArrayList<UserProduct> foodList = Examples.getAllFood();
        foodList.addAll(Examples.getAllFood()); // для количества
        recyclerView = (RecyclerView) findViewById(R.id.food_list_recycler_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        mAdapter = new FoodAdapter(foodList);
        recyclerView.setAdapter(mAdapter);
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
}

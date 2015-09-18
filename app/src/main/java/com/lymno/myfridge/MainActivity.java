package com.lymno.myfridge;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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
import com.mikepenz.materialize.util.UIUtils;

public class MainActivity extends AppCompatActivity {
    private static final int PROFILE_SETTING = 1;

    //save our header or result
    private AccountHeader headerResult = null;
    private Drawer result = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Handle Toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("title");

        // Create a few sample profile
        final IProfile profile = new ProfileDrawerItem().withName("Mike Penz").withEmail("mikepenz@gmail.com").withIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_add));

        // Create the AccountHeader
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(true)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(
                        profile,
                        //don't ask but google uses 14dp for the add account icon in gmail but 20dp for the normal icons (like manage account)
                        new ProfileSettingDrawerItem().withName("Add Account").withDescription("Add new GitHub Account").withIcon(new IconicsDrawable(this, GoogleMaterial.Icon.gmd_add).actionBarSize().paddingDp(5).colorRes(R.color.material_drawer_dark_primary_text)).withIdentifier(PROFILE_SETTING),
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
                        new PrimaryDrawerItem().withName(R.string.drawer_item_food).withIcon(FontAwesome.Icon.faw_home).withIdentifier(1),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_recipes).withIcon(FontAwesome.Icon.faw_gamepad),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_recommended_recipies).withIcon(FontAwesome.Icon.faw_eye),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_menu).withIcon(FontAwesome.Icon.faw_github),
                        new SectionDrawerItem().withName("R.string.drawer_item_section_header"),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_settings).withIcon(FontAwesome.Icon.faw_cog),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_help).withIcon(FontAwesome.Icon.faw_question).withEnabled(false),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_contact).withIcon(FontAwesome.Icon.faw_bullhorn)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem != null && drawerItem.getIdentifier() == 1) {
                            //startSupportActionMode(new ActionBarCallBack());
                            //findViewById(R.id.action_mode_bar).setBackgroundColor(UIUtils.getThemeColorFromAttrOrRes(MainActivity.this, R.attr.colorPrimary, R.color.material_drawer_primary));
                        }

                        if (drawerItem instanceof Nameable) {
                            toolbar.setTitle(((Nameable) drawerItem).getName().getText(MainActivity.this));
                        }

                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

        // set the selection to the item with the identifier 5
        result.setSelection(5, false);

        //set the back arrow in the toolbar
        //getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        //getSupportActionBar().setHomeButtonEnabled(true);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle the click on the back arrow click
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    class ActionBarCallBack implements ActionMode.Callback {

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return false;
        }

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(UIUtils.getThemeColorFromAttrOrRes(MainActivity.this, R.attr.colorPrimaryDark, R.color.material_drawer_primary_dark));
            }
//bug
//            mode.getMenuInflater().inflate(R.menu.cab, menu);
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }
    }
}

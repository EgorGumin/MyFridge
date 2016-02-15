package com.lymno.myfridge.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lymno.myfridge.R;
import com.lymno.myfridge.model.Recipe;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;


public class RecipeActivity extends AppCompatActivity {

    private static Recipe mRecipe;

    public static void open(Context context, Recipe recipe) {
        mRecipe = recipe;
        Intent intent = new Intent(context, RecipeActivity.class);
        context.startActivity(intent);
    }

    @Bind(R.id.imageView)
    protected ImageView mImageView;

    @Bind(R.id.tvRecipeDescription)
    protected TextView descrip;

    @Bind(R.id.collapsing_toolbar_recipe_layout)
    protected CollapsingToolbarLayout toolbarLayout;

    @Bind(R.id.toolbar_recipe)
    protected Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_info_new);

        ButterKnife.bind(this);
        load();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        toolbarLayout.setTitle(mRecipe.getName());
    }


    private void load() {
        descrip.setText(mRecipe.getDescription());

        Picasso.with(mImageView.getContext()).load(mRecipe.getMainImage()).
                error(R.drawable.no_image).into(mImageView);
    }
}

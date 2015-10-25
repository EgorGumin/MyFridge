package com.lymno.myfridge.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.lymno.myfridge.R;
import com.lymno.myfridge.model.Recipe;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Andre on 25.10.2015.
 */
public class RecipeActivity extends AppCompatActivity{

    private static Recipe mRecipe;
    public static void open(Context context,Recipe recipe){
        mRecipe=recipe;
        Intent intent=new Intent(context,RecipeActivity.class);
        context.startActivity(intent);
    }

    @Bind(R.id.imageView)
    protected ImageView mImageView;

    @Bind(R.id.tvCardRecipeName)
    protected TextView mTextRecipeName;

    @Bind(R.id.tvRecipeDescription)
    protected TextView descrip;

    @Bind(R.id.tvRecipeFoodList)
    protected TextView list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_info);
        ButterKnife.bind(this);
        load();

        // Handle Toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Описание рецепта");

    }


    private void load(){
        mTextRecipeName.setText(mRecipe.getName());
        descrip.setText(mRecipe.getDescription());

        Picasso.with(mImageView.getContext()).load(mRecipe.getImageUrl()).
                error(R.drawable.rec1).into(mImageView);
    }

}

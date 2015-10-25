package com.lymno.myfridge.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lymno.myfridge.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Andre on 24.10.2015.
 */
public class FoodInfoActivity extends AppCompatActivity {

    public static final String INTENT_CATEGORY_STRING="cat";
    public static final String INTENT_NAME_STRING="nam";
    public static final String INTENT_COUNT_INT="count";
    public static final String INTENT_COUNT_IN_PACKET_INT="count_in_packet";
    public static final String INTENT_UNITS_STRING="units";
    public static final String INTENT_USE_LEFT_STRING ="use_before";
    public static final String INTENT_BAR_CODE_STRING ="bar_code";

    @Bind(R.id.food_info_icon)
    protected ImageView mFoodIcon;

    @Bind(R.id.food_info_category)
    protected TextView mCategoryName;

    @Bind(R.id.food_info_eat_before)
    protected TextView mEatBefore;

    @Bind(R.id.food_info_progress_eat)
    protected SeekBar mEatLeft;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_info);
        ButterKnife.bind(this);

        loadFromIntent();
        mEatLeft.setOnSeekBarChangeListener(new SeekListener());


        // Handle Toolbar
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("title");

    }


    private void loadFromIntent(){
        Intent loadedIntent=getIntent();
        if (loadedIntent!=null){
            mCategoryName.setText(loadedIntent.getStringExtra(INTENT_CATEGORY_STRING) + "  "
                    + loadedIntent.getStringExtra(INTENT_NAME_STRING));

            loadedIntent.getStringExtra(INTENT_UNITS_STRING);
            mEatBefore.setText(loadedIntent.getStringExtra(INTENT_USE_LEFT_STRING));
            loadedIntent.getStringExtra(INTENT_BAR_CODE_STRING);

            mEatLeft.setMax(loadedIntent.getIntExtra(INTENT_COUNT_IN_PACKET_INT, 100));
            mEatLeft.setProgress(loadedIntent.getIntExtra(INTENT_COUNT_INT, 100));
        }
    }


    private class SeekListener implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}

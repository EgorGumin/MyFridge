package com.lymno.myfridge.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.lymno.myfridge.R;

import butterknife.Bind;

public class FoodInfoActivity extends AppCompatActivity {

    public static final String INTENT_PRODUCT_ID = "id";
    public static final String INTENT_CATEGORY_STRING = "cat";
    public static final String INTENT_NAME_STRING = "nam";
    public static final String INTENT_COUNT_INT = "count";
    public static final String INTENT_COUNT_IN_PACKET_INT = "count_in_packet";
    public static final String INTENT_UNITS_STRING = "units";
    public static final String INTENT_USE_LEFT_STRING = "use_before";
    public static final String INTENT_BAR_CODE_STRING = "bar_code";

    @Bind(R.id.food_info_icon)
    protected ImageView mFoodIcon;

    @Bind(R.id.food_info_category)
    protected TextView mCategoryName;

    @Bind(R.id.food_info_eat_before)
    protected TextView mEatBefore;

    @Bind(R.id.food_info_toolbar)
    protected Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);
//        ButterKnife.bind(this);

        //loadFromIntent();

        // Handle Toolbar
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("Описание Продукта");
//        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }


    private void loadFromIntent() {
        Intent loadedIntent = getIntent();
        if (loadedIntent != null) {
            mCategoryName.setText(loadedIntent.getStringExtra(INTENT_CATEGORY_STRING) + "  "
                    + loadedIntent.getStringExtra(INTENT_NAME_STRING));

            loadedIntent.getStringExtra(INTENT_UNITS_STRING);
            mEatBefore.setText(loadedIntent.getStringExtra(INTENT_USE_LEFT_STRING));
            loadedIntent.getStringExtra(INTENT_BAR_CODE_STRING);
        }
    }
}

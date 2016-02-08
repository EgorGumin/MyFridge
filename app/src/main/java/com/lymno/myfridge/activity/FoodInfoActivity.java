package com.lymno.myfridge.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.lymno.myfridge.R;
import com.lymno.myfridge.model.UserProduct;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FoodInfoActivity extends AppCompatActivity {

    public static final String INTENT_PRODUCT_ID = "id";

    private UserProduct product;

    @Bind(R.id.pr_info_tv_pr_name)
    protected TextView tvProductName;

    @Bind(R.id.pr_info_collapsing_toolbar_layout)
    protected CollapsingToolbarLayout toolbarLayout;

    @Bind(R.id.pr_info_toolbar)
    protected Toolbar toolbar;

    @Bind(R.id.pr_info_seekbar_pr)
    protected SeekBar sbQuantity;

    @Bind(R.id.pr_info_tv_pr_left_name)
    protected TextView tvProdLeftName;

    @Bind(R.id.pr_info_tv_pack_size)
    protected TextView tvPackSize;

    @Bind(R.id.pr_info_btn_cancel_changes)
    protected Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);
        ButterKnife.bind(this);

        product = UserProduct.getByID(getIntent().getLongExtra(INTENT_PRODUCT_ID, 0));

        tvProductName.setText(product.getCategory() + "  "
                + product.getName());


        sbQuantity.setMax(product.getQuantity());
        sbQuantity.setProgress(product.getQuantity());
        tvProdLeftName.setText("Осталось: " + product.getQuantity() + " " + product.getMeasure());
        tvPackSize.setText("В упаковке: " + product.getQuantityByDefault()
                + " " + product.getMeasure());

        sbQuantity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvProdLeftName.setText("Осталось: " + sbQuantity.getProgress() + " " + product.getMeasure());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
//    TODO добавить отображение штрих-кода

    @OnClick(R.id.pr_info_btn_cancel_changes)
    public void cancel() {
        sbQuantity.setProgress(product.getQuantity());
    }
}

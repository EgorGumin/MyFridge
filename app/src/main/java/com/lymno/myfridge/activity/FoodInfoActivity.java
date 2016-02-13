package com.lymno.myfridge.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.lymno.myfridge.R;
import com.lymno.myfridge.model.NewAmount;
import com.lymno.myfridge.model.UserProduct;
import com.lymno.myfridge.network.Api;
import com.lymno.myfridge.network.RestClient;

import java.text.SimpleDateFormat;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FoodInfoActivity extends AppCompatActivity {

    public static final String INTENT_PRODUCT_ID = "id";

    private UserProduct product;
    private SharedPreferences settings;
    private String tokenKey = "com.lymno.myfridge.activity.token";
    private String token;
    private final Api api = RestClient.get();

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

    @Bind(R.id.pr_info_tv_use_before_date)
    protected TextView tvUseBeforeDate;

    @Bind(R.id.pr_info_btn_cancel_changes)
    protected Button btnCancel;

    @Bind(R.id.pr_info_btn_save_changes)
    protected Button btnSave;

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

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        tvUseBeforeDate.setText(dateFormat.format(product.getDate()));

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

        settings = this.getSharedPreferences("com.lymno.myfridge.activity", Context.MODE_PRIVATE);
        token = settings.getString(tokenKey, "");
    }
//    TODO добавить отображение штрих-кода

    @OnClick(R.id.pr_info_btn_cancel_changes)
    public void cancel() {
        sbQuantity.setProgress(product.getQuantity());
    }

    @OnClick(R.id.pr_info_btn_save_changes)
    public void save() {
        if (sbQuantity.getProgress() == product.getQuantity()) {
            Toast.makeText(FoodInfoActivity.this, "Saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(FoodInfoActivity.this, "Saving...", Toast.LENGTH_SHORT).show();
            final int progress = sbQuantity.getProgress();
            NewAmount newAmount = new NewAmount(product.getUserProductID(), progress);
            btnCancel.setEnabled(false);
            btnSave.setEnabled(false);
            api.changeAmount(token, newAmount, new Callback<Void>() {
                @Override
                public void success(Void aVoid, Response response) {
                    if (progress == 0) {
//                        // TODO: 12.02.2016 Проверить перезагрузку MainActivity после закрытия этой, дописать исчезновение контролов и подсказки
                        product.delete();
                        Toast.makeText(FoodInfoActivity.this, "Продукт удален", Toast.LENGTH_SHORT).show();
                        FoodInfoActivity.this.finish();
                    } else {
                        product.setQuantity(progress);
                        product.save();
                        Toast.makeText(FoodInfoActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                        sbQuantity.setProgress(progress);
                        sbQuantity.setMax(progress);
                    }


                    btnCancel.setEnabled(true);
                    btnSave.setEnabled(true);
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(FoodInfoActivity.this, "Error: " + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    btnCancel.setEnabled(true);
                    btnSave.setEnabled(true);
                }
            });
        }
    }
}

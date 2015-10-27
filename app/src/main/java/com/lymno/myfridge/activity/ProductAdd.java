package com.lymno.myfridge.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.lymno.myfridge.Categories;
import com.lymno.myfridge.Measures;
import com.lymno.myfridge.R;
import com.lymno.myfridge.model.ProductSearchResult;
import com.lymno.myfridge.network.BaseSampleSpiceActivity;
import com.lymno.myfridge.network.SampleRetrofitSpiceService;
import com.lymno.myfridge.network.request.ProductSearch;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class ProductAdd extends BaseSampleSpiceActivity {
    ProductSearch test;
    TextView barcode;
    TextView category;
    TextView name;
    TextView quantityByDefault;
    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);
        Intent intent = getIntent();
        String code = intent.getStringExtra("barcode");
        category = (TextView) findViewById(R.id.product_add_category);
        name = (TextView) findViewById(R.id.product_add_name);
        barcode = (TextView) findViewById(R.id.product_add_barcode);
        quantityByDefault = (TextView) findViewById(R.id.product_add_quantity_by_default);
        barcode.setText(code);

        test = new ProductSearch(code, SampleRetrofitSpiceService.getSessionId());
        getSpiceManager().execute(test, "test", DurationInMillis.ONE_MINUTE, new ListContributorRequestListener());
        progress = ProgressDialog.show(this, "Поиск информации...",
                "Пожалуйста, подождите", true);
    }



    private void updateContributors(final ProductSearchResult result) {
        name.setText(result.getName());
        category.setText(Categories.getItem(result.getCategoryID()));
        String quantityByDefaultText = "*" + result.getAmountDefault() + " " + Measures.getItem(result.getUnitMeasureID());
        quantityByDefault.setText(quantityByDefaultText);
    }

    public final class ListContributorRequestListener implements RequestListener<ProductSearchResult> {
        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(ProductAdd.this, "Failure: " + spiceException.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            progress.dismiss();
        }

        @Override
        public void onRequestSuccess(final ProductSearchResult result) {
            progress.dismiss();
            Toast.makeText(ProductAdd.this, "success", Toast.LENGTH_SHORT).show();
            if (result != null){
                updateContributors(result);
            }
            else{
                Toast.makeText(ProductAdd.this, "Неправильный тип кода или такого продукта еще нет в базе.", Toast.LENGTH_LONG).show();
            }

        }
    }
}
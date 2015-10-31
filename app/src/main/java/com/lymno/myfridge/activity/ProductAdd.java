package com.lymno.myfridge.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lymno.myfridge.Categories;
import com.lymno.myfridge.DatePicker;
import com.lymno.myfridge.Measures;
import com.lymno.myfridge.R;
import com.lymno.myfridge.database.UserProductsDatabase;
import com.lymno.myfridge.model.ProductSearchResult;
import com.lymno.myfridge.model.UserProduct;
import com.lymno.myfridge.model.UserProductId;
import com.lymno.myfridge.network.BaseSampleSpiceActivity;
import com.lymno.myfridge.network.SampleRetrofitSpiceService;
import com.lymno.myfridge.network.request.ProductSearch;
import com.lymno.myfridge.network.request.PushProduct;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductAdd extends BaseSampleSpiceActivity implements View.OnClickListener {
    ProductSearch test;
    TextView barcode;
    TextView category;
    TextView name;
    TextView quantityByDefault;
    TextView addQuantity;
    TextView date;
    ProgressDialog progress;
    Button save;
    int productID;
    ProductSearchResult product;


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
        addQuantity = (TextView) findViewById(R.id.product_add_quantity);
        date = (TextView) findViewById(R.id.product_add_date);
        date.setOnClickListener(this);
        save = (Button) findViewById(R.id.add_product_button_save);
        save.setOnClickListener(this);

        barcode.setText(code);

        test = new ProductSearch(code, SampleRetrofitSpiceService.getSessionId());
        getSpiceManager().execute(test, "test", DurationInMillis.ONE_MINUTE, new ListContributorRequestListener());
        progress = ProgressDialog.show(this, "Поиск информации...",
                "Пожалуйста, подождите", true);
    }

    public void onClick(View v) {
        if (v == date) {
            DialogFragment dateDialog = new DatePicker();
            dateDialog.show(getSupportFragmentManager(), "datePicker");
        }
        if ((v == save) & (productID != 0)) {
            PushProduct saveProduct = new PushProduct(productID, Integer.valueOf(addQuantity.getText().toString()), date.getText().toString());
            getSpiceManager().execute(saveProduct, "Save Product", DurationInMillis.ONE_MINUTE, new SaveProductRequestListener());
        }
    }


    private void updateContributors(final ProductSearchResult result) {
        product = result;
        productID = result.getProductID();
        name.setText(result.getName());
        category.setText(Categories.getItem(result.getCategoryID()));
        String quantityByDefaultText = "*" + result.getAmountDefault() + " " + Measures.getItem(result.getUnitMeasureID());
        quantityByDefault.setText(quantityByDefaultText);
    }

    public final class ListContributorRequestListener implements RequestListener<ProductSearchResult> {
        @Override
        public void onRequestFailure(SpiceException spiceException) {
            //Пока Влад не поправил ошибку на нулл, переходим из этой ветки
            //Toast.makeText(ProductAdd.this, "Failure: " + spiceException.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            progress.dismiss();
            //new activity
            Intent intent = new Intent(ProductAdd.this, ProductAddNew.class);
            startActivity(intent);
        }

        @Override
        public void onRequestSuccess(final ProductSearchResult result) {
            progress.dismiss();
            Toast.makeText(ProductAdd.this, "success", Toast.LENGTH_SHORT).show();
            if (result != null) {
                updateContributors(result);
            } else {
                Toast.makeText(ProductAdd.this, "Неправильный тип кода или такого продукта еще нет в базе.", Toast.LENGTH_LONG).show();
            }

        }
    }

    public final class SaveProductRequestListener implements RequestListener<UserProductId> {
        @Override
        public void onRequestFailure(SpiceException spiceException) {
            //Пока Влад не поправил ошибку на нулл, переходим из этой ветки
            //Toast.makeText(ProductAdd.this, "Failure: " + spiceException.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            progress.dismiss();
            //new activity
        }

        @Override
        public void onRequestSuccess(final UserProductId result) {
            progress.dismiss();
            Toast.makeText(ProductAdd.this, "success", Toast.LENGTH_SHORT).show();
            if (result != null) {
                //добавить в базу
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                try {
                    Date myDate = simpleDateFormat.parse(date.getText().toString());
                    UserProduct userProduct = new UserProduct(result.getId(), 1,
                            product.getProductID(), product.getCategoryID(), product.getName(),
                            product.getUnitMeasureID(), Integer.valueOf(addQuantity.getText().toString()),
                            product.getAmountDefault(), myDate);
                    UserProductsDatabase.addUserProduct(userProduct);
                    Intent intent = new Intent(ProductAdd.this, MainActivity.class);
                    startActivity(intent);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(ProductAdd.this, "Неправильный тип кода или такого продукта еще нет в базе.", Toast.LENGTH_LONG).show();
            }

        }
    }
}
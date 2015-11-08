package com.lymno.myfridge.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lymno.myfridge.Categories;
import com.lymno.myfridge.DatePicker;
import com.lymno.myfridge.Measures;
import com.lymno.myfridge.R;
import com.lymno.myfridge.model.ProductSearchResult;
import com.lymno.myfridge.model.UserProduct;
import com.lymno.myfridge.model.UserProductId;
import com.lymno.myfridge.network.Api;
import com.lymno.myfridge.network.RestClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ProductAdd extends AppCompatActivity implements View.OnClickListener {
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
    final Api api = RestClient.get();

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

        api.searchBarcode(code, RestClient.getSessionId(), new Callback<ProductSearchResult>() {
            @Override
            public void success(ProductSearchResult productSearchResult, Response response) {
                progress.dismiss();
                Toast.makeText(ProductAdd.this, "success", Toast.LENGTH_SHORT).show();
                if (productSearchResult != null) {
                    updateContributors(productSearchResult);
                } else {
                    Intent intent = new Intent(ProductAdd.this, ProductAddNew.class);
                    intent.putExtra("barcode", barcode.getText().toString());
                    startActivity(intent);
                    //Toast.makeText(ProductAdd.this, "Неправильный тип кода или такого продукта еще нет в базе.", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                //Пока Влад не поправил ошибку на нулл, переходим из этой ветки
                //Toast.makeText(ProductAdd.this, "Failure: " + spiceException.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                progress.dismiss();
                //new activity
                Intent intent = new Intent(ProductAdd.this, ProductAddNew.class);
                intent.putExtra("barcode", barcode.getText().toString());
                startActivity(intent);
            }
        });

        //test = new ProductSearch(code, SampleRetrofitSpiceService.getSessionId());
        //getSpiceManager().execute(test, "test", DurationInMillis.ONE_MINUTE, new ListContributorRequestListener());

        progress = ProgressDialog.show(this, "Поиск информации...",
                "Пожалуйста, подождите", true);
    }

    public void onClick(View v) {
        if (v == date) {
            DialogFragment dateDialog = new DatePicker();
            dateDialog.show(getSupportFragmentManager(), "datePicker");
        }
        if ((v == save) & (productID != 0)) {
            api.addProd(productID, Integer.valueOf(addQuantity.getText().toString()), date.getText().toString(), "4", new Callback<UserProductId>() {
                @Override
                public void success(UserProductId userProductId, Response response) {
                    progress.dismiss();
                    Toast.makeText(ProductAdd.this, "success", Toast.LENGTH_SHORT).show();
                    if (userProductId != null) {
                        //добавить в базу
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                        try {
                            Date myDate = simpleDateFormat.parse(date.getText().toString());
                            UserProduct userProduct = new UserProduct(userProductId.getId(), 1,
                                    product.getProductID(), product.getCategoryID(), product.getName(),
                                    product.getUnitMeasureID(), Integer.valueOf(addQuantity.getText().toString()),
                                    product.getAmountDefault(), myDate);
                            userProduct.save();
                            Intent intent = new Intent(ProductAdd.this, MainActivity.class);
                            startActivity(intent);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(ProductAdd.this, "Неправильный тип кода или такого продукта еще нет в базе.", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    //Пока Влад не поправил ошибку на нулл, переходим из этой ветки
                    //Toast.makeText(ProductAdd.this, "Failure: " + spiceException.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                    //new activity
                }
            });
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
}
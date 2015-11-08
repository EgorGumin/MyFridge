package com.lymno.myfridge.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
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

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ProductAdd extends AppCompatActivity {
    @Bind(R.id.product_add_barcode)
    TextView barcode;
    @Bind(R.id.product_add_category)
    TextView category;
    @Bind(R.id.product_add_name)
    TextView name;
    @Bind(R.id.product_add_quantity)
    TextView addQuantity;
    @Bind(R.id.product_add_date)
    TextView date;
    @Bind(R.id.product_add_quantity_by_default)
    TextView quantityByDefault;
    @Bind(R.id.add_product_button_save)
    Button save;
    ProgressDialog progress;

    private int productID;
    private ProductSearchResult product;
    private final Api api = RestClient.get();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_PROGRESS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String code = intent.getStringExtra("barcode");
        barcode.setText(code);

        api.searchBarcode(code, RestClient.getSessionId(), new Callback<ProductSearchResult>() {
            @Override
            public void success(ProductSearchResult productSearchResult, Response response) {
                progress.dismiss();
                if (productSearchResult != null) {
                    product = productSearchResult;
                    productID = productSearchResult.getProductID();
                    name.setText(productSearchResult.getName());
                    category.setText(Categories.getItem(productSearchResult.getCategoryID()));
                    String quantityByDefaultText = "*" + productSearchResult.getAmountDefault() +
                            " " + Measures.getItem(productSearchResult.getUnitMeasureID());
                    quantityByDefault.setText(quantityByDefaultText);
                } else {
                    Intent intent = new Intent(ProductAdd.this, ProductAddNew.class);
                    intent.putExtra("barcode", barcode.getText().toString());
                    startActivity(intent);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                progress.dismiss();
                Intent intent = new Intent(ProductAdd.this, ProductAddNew.class);
                intent.putExtra("barcode", barcode.getText().toString());
                startActivity(intent);
            }
        });

        progress = ProgressDialog.show(this, "Поиск информации...", "Пожалуйста, подождите", true);
    }

    @OnClick(R.id.product_add_date)
    public void addDate() {
        DialogFragment dateDialog = new DatePicker();
        dateDialog.show(getSupportFragmentManager(), "datePicker");
    }

    @OnClick(R.id.add_product_button_save)
    public void save() {
        if (productID != 0) {
            api.addProd(productID, Integer.valueOf(addQuantity.getText().toString()), date.getText().toString(), "4", new Callback<UserProductId>() {
                @Override
                public void success(UserProductId userProductId, Response response) {
                    progress.dismiss();
                    Toast.makeText(ProductAdd.this, "Добавлено!", Toast.LENGTH_SHORT).show();
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
                    progress.dismiss();
                }
            });
        }
    }
}

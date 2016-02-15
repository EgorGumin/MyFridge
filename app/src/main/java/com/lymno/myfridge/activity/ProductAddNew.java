package com.lymno.myfridge.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lymno.myfridge.DatePickerForAddNew;
import com.lymno.myfridge.Measures;
import com.lymno.myfridge.R;
import com.lymno.myfridge.model.Category;
import com.lymno.myfridge.model.NewProductAddResult;
import com.lymno.myfridge.model.UserProduct;
import com.lymno.myfridge.model.UserProductNotExisting;
import com.lymno.myfridge.network.Api;
import com.lymno.myfridge.network.RestClient;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ProductAddNew extends AppCompatActivity implements View.OnClickListener {
    private AutoCompleteTextView autoComplete;
    private ArrayAdapter<String> adapter;
    private EditText name;
    private TextView barcode;
    private EditText quantity;
    private TextView date;
    private Button save;
    private Spinner measure;
    private ArrayList<String> categories;
    private String measureID;
    private int categoryID;
    private String barcodeText;
    private String nameText;
    private int amountDef;
    private String dateText;
    private int quantityText;
    private String code;
    final Api api = RestClient.get();
    private ArrayList<String> measures;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add_new);
        Intent intent = getIntent();
        code = intent.getStringExtra("barcode");

        name = (EditText) findViewById(R.id.add_new_name);
        barcode = (TextView) findViewById(R.id.add_new_barcode);
        barcode.setText(code);
        quantity = (EditText) findViewById(R.id.add_new_quantity);
        save = (Button) findViewById(R.id.add_new_button_save);
        save.setOnClickListener(this);
        measure = (Spinner) findViewById(R.id.add_new_measure_spinner);

        date = (TextView) findViewById(R.id.add_new_date_add);
        date.setOnClickListener(this);

        //autocomplete
        categories = Category.getTextArrayList();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, categories);
        autoComplete = (AutoCompleteTextView) findViewById(R.id.add_new_category);
        // set adapter for the auto complete fields
        autoComplete.setAdapter(adapter);
        // specify the minimum type of characters before drop-down list is shown
        autoComplete.setThreshold(1);

        //measure
        // адаптер
        measures = Measures.get();
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, measures);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        measure.setAdapter(adapterSpinner);
        // заголовок
        measure.setPrompt("Title");
        // выделяем элемент
        measure.setSelection(2);
        // устанавливаем обработчик нажатия
        measure.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // показываем позиция нажатого элемента
                Toast.makeText(getBaseContext(), "Position = " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    public void onClick(View v) {
        if (v == date) {
            DialogFragment dateDialog = new DatePickerForAddNew();
            dateDialog.show(getSupportFragmentManager(), "datePicker");
        }
        //todo все это нужно переписывать, господи =(((((((((((((((((((((
        if (v == save) {
            measureID = measures.get(measure.getSelectedItemPosition());
            //categoryID = categories.indexOf(autoComplete.getText().toString()) + 1;
            //categoryID = Categories.getID(autoComplete.getText().toString());
            nameText = name.getText().toString();
//            if (measureID == 3) {
//                amountDef = 0;
//            } else {
//                amountDef = Integer.valueOf(quantity.getText().toString());
//            }
            amountDef = Integer.valueOf(quantity.getText().toString());
            dateText = date.getText().toString();
            quantityText = Integer.valueOf(quantity.getText().toString());
            Toast.makeText(ProductAddNew.this, measureID + " " + categoryID, Toast.LENGTH_LONG).show();

            final UserProductNotExisting productNE = new UserProductNotExisting(code, nameText,
                    autoComplete.getText().toString(), quantityText, amountDef, measureID, dateText);

            SharedPreferences settings = this.getSharedPreferences(
                    "com.lymno.myfridge.activity", Context.MODE_PRIVATE);
            String token = settings.getString(getResources().getString(R.string.token_key), "");


            api.addNewProduct(token, productNE, new Callback<NewProductAddResult>() {
                @Override
                public void success(NewProductAddResult newProductAddResult, Response response) {
                    Toast.makeText(ProductAddNew.this, "success", Toast.LENGTH_SHORT).show();
                    if (newProductAddResult != null) {
                        //добавить в базу
                        UserProduct userProduct = new UserProduct(productNE, newProductAddResult);
                        userProduct.save();

                        Intent intent = new Intent(ProductAddNew.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ProductAddNew.this,
                                "Неправильный тип кода или такого продукта еще нет в базе.",
                                Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    //Пока Влад не поправил ошибку на нулл, переходим из этой ветки
                    Toast.makeText(ProductAddNew.this, "Failure: " + error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    //new activity
                }
            });
        }
    }
}

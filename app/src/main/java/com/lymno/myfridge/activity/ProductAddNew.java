package com.lymno.myfridge.activity;

import android.content.DialogInterface;
import android.content.Intent;
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

import com.lymno.myfridge.Categories;
import com.lymno.myfridge.DatePicker;
import com.lymno.myfridge.DatePickerForAddNew;
import com.lymno.myfridge.Measures;
import com.lymno.myfridge.R;
import com.lymno.myfridge.database.UserProductsDatabase;
import com.lymno.myfridge.model.NewProductAddResult;
import com.lymno.myfridge.model.UserProduct;
import com.lymno.myfridge.network.Api;
import com.lymno.myfridge.network.BaseSampleSpiceActivity;
import com.lymno.myfridge.network.RestClient;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ProductAddNew extends BaseSampleSpiceActivity implements View.OnClickListener{
    private AutoCompleteTextView autoComplete;
    private ArrayAdapter<String> adapter;
    private EditText name;
    private TextView barcode;
    private EditText quantity;
    private TextView date;
    private Button save;
    private Spinner measure;
    private ArrayList<String> categories;

    private int measureID;
    private int categoryID;
    private String barcodeText;
    private String nameText;
    private int amountDef;
    private String dateText;
    private int quantityText;
    private String code;
    final Api api = RestClient.get();


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
        // get the defined string-array
        categories = Categories.get();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categories);

        autoComplete = (AutoCompleteTextView) findViewById(R.id.add_new_category);

        // set adapter for the auto complete fields
        autoComplete.setAdapter(adapter);

        // specify the minimum type of characters before drop-down list is shown
        autoComplete.setThreshold(1);


        //measure
        // адаптер
        ArrayList<String> measures = Measures.get();
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, measures);
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
        if (v == save) {
            measureID = measure.getSelectedItemPosition() + 1;
            categoryID = categories.indexOf(autoComplete.getText().toString()) + 1;
            nameText = name.getText().toString();
            if(measureID == 3){
                amountDef = 0;
            }
            else {
                amountDef = Integer.valueOf(quantity.getText().toString());
            }
            dateText = date.getText().toString();
            quantityText = Integer.valueOf(quantity.getText().toString());
            Toast.makeText(ProductAddNew.this, measureID + " " + categoryID, Toast.LENGTH_LONG).show();

            api.addNewProduct("4", 1, code, categoryID, nameText, amountDef, measureID, quantityText, dateText, new Callback<NewProductAddResult>() {
                @Override
                public void success(NewProductAddResult newProductAddResult, Response response) {
                    Toast.makeText(ProductAddNew.this, "success", Toast.LENGTH_SHORT).show();
                    if (newProductAddResult != null) {
                        //добавить в базу
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                        try {
                            Date myDate = simpleDateFormat.parse(date.getText().toString());
                            UserProduct userProduct = new UserProduct(newProductAddResult.getUserProductID(), 1,
                                    newProductAddResult.getProductID(),
                                    categoryID, nameText,
                                    measureID, quantityText,
                                    amountDef, myDate);
                            UserProductsDatabase.addUserProduct(userProduct);

                            Intent intent = new Intent(ProductAddNew.this, MainActivity.class);
                            startActivity(intent);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(ProductAddNew.this, "Неправильный тип кода или такого продукта еще нет в базе.", Toast.LENGTH_LONG).show();
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

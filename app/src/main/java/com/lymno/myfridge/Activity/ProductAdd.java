package com.lymno.myfridge.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.lymno.myfridge.R;

public class ProductAdd extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_add);
        Intent intent = getIntent();
        String code = intent.getStringExtra("barcode");
        TextView barcode = (TextView) findViewById(R.id.barcode);
        barcode.setText(code);
    }
}

package com.lymno.myfridge.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.lymno.myfridge.Categories;

public class CheckDataActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Categories.get() == null) {
            Intent intent = new Intent(CheckDataActivity.this, LoadDataActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(CheckDataActivity.this, LoginActivity.class);
            startActivity(intent);
        }

    }
}

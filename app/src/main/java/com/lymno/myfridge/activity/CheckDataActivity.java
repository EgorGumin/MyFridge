package com.lymno.myfridge.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.lymno.myfridge.model.Category;

public class CheckDataActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Boolean lst = Category.getAll().isEmpty();
        if (Category.getAll().isEmpty()) {
            Intent intent = new Intent(CheckDataActivity.this, LoadDataActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(CheckDataActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }
}

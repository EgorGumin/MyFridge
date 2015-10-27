package com.lymno.myfridge.barcode_scanner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lymno.myfridge.R;

public class ScannerFragmentActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.barcode_scanner);
    }
}

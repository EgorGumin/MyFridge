package com.lymno.myfridge.barcode_scanner;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.lymno.myfridge.R;

public class ScannerFragmentActivity extends ActionBarActivity {
    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.barcide_scanner);
    }
}
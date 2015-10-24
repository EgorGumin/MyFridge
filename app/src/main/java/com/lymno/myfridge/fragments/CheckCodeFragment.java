package com.lymno.myfridge.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.lymno.myfridge.R;
import com.lymno.myfridge.network.request.ProductSearch;
import com.lymno.myfridge.views.BucketView;

import butterknife.ButterKnife;

/**
 * Created by Andre on 24.10.2015.
 */
public class CheckCodeFragment extends Fragment {

    private ProductSearch mSearchBarcodeRequest;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflated=inflater.inflate(R.layout.check_barcode_fragment,container,false);
        ButterKnife.bind(inflated);
        return inflated;
    }


}

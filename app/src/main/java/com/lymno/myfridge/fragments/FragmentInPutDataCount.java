package com.lymno.myfridge.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lymno.myfridge.R;

import butterknife.ButterKnife;

/**
 * Created by Andre on 24.10.2015.
 */
public class FragmentInPutDataCount extends Fragment {

    private String mBarCode = null;

    public static FragmentInPutDataCount create(String barCode) {
        FragmentInPutDataCount fragmentInPutDataCount = new FragmentInPutDataCount();
        fragmentInPutDataCount.mBarCode = barCode;
        return fragmentInPutDataCount;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflated=inflater.inflate(R.layout.input_data_count_fragment,container,false);
        ButterKnife.bind(inflated);
        return inflated;
    }
}

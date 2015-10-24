package com.lymno.myfridge.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lymno.myfridge.R;

/**
 * Created by Andre on 24.10.2015.
 */
public class CheckCodeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.check_barcode_fragment,container,false);
    }
}

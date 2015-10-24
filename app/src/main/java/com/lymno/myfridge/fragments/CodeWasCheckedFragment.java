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
public class CodeWasCheckedFragment extends Fragment {

    private final static String IS_SUCCESS= "is_suc";

    public static CheckCodeFragment create(boolean isSuccess){
        Bundle bundle=new Bundle();
        bundle.putBoolean(IS_SUCCESS,isSuccess);
        CheckCodeFragment checkCodeFragment=new CheckCodeFragment();
        checkCodeFragment.setArguments(bundle);
        return  checkCodeFragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflated=inflater.inflate(R.layout.two_buttons_fragment,container,false);
        ButterKnife.bind(inflated);
        return inflated;
    }
}

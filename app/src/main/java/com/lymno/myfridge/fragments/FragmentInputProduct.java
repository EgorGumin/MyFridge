package com.lymno.myfridge.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.lymno.myfridge.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Andre on 25.10.2015.
 */
public class FragmentInputProduct  extends Fragment {

    @Bind(R.id.input_count)
    protected EditText mInputCount;
    @Bind(R.id.input_didline)
    protected EditText mInputDidLine;

    private String mBarCode = null;

    public static FragmentInputProduct create(String barCode) {
        FragmentInputProduct fragmentInputProduct = new FragmentInputProduct();
        fragmentInputProduct.mBarCode = barCode;
        return fragmentInputProduct;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflated=inflater.inflate(R.layout.input_product,container,false);
        ButterKnife.bind(inflated);
        inflated.findViewById(R.id.push).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                push();
            }
        });
        return inflated;
    }

    private void push(){
        //todo
    }
}

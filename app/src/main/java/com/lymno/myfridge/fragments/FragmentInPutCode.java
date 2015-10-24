package com.lymno.myfridge.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lymno.myfridge.R;
import com.lymno.myfridge.model.ProductSearchResult;
import com.lymno.myfridge.network.BaseSampleSpiceFragment;
import com.lymno.myfridge.network.request.ProductSearch;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Andre on 24.10.2015.
 */
public class FragmentInPutCode extends BaseSampleSpiceFragment {

    private ProductSearch mSearchBarcodeRequest;
    private String mBarCode = null;
    protected Button mPushCode;
    protected EditText mInputBarCode;

    public static FragmentInPutCode create(String barCode) {
        FragmentInPutCode fragmentInPutCode = new FragmentInPutCode();
        fragmentInPutCode.mBarCode = barCode;
        return fragmentInPutCode;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflated = inflater.inflate(R.layout.input_code_fragment, container, false);
        mPushCode = (Button) inflated.findViewById(R.id.input_barcode_push);
        mInputBarCode = (EditText) inflated.findViewById(R.id.input_barcode);
        mPushCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushBarCode();
            }
        });
        if (mBarCode != null) {
            pushBarCode();
        }
        return inflated;
    }


    @Override
    public void onStart() {
        super.onStart();
        trySendRequest();
    }


    private void updateContributors(final ProductSearchResult result) {
        mInputBarCode.setText(result.getName()/* + result.getDescription()*/);
    }


    public final class ListContributorRequestListener implements RequestListener<ProductSearchResult> {
        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(getActivity(), "Failure: " + spiceException.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            inputBarCode();
        }

        @Override
        public void onRequestSuccess(final ProductSearchResult result) {
            Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
            if (result != null) {
                updateContributors(result);
            } else {
                Toast.makeText(getActivity(), "Неправильный тип кода или такого продукта еще нет в базе.", Toast.LENGTH_LONG).show();
                //TODO make open input data product
            }

        }
    }



    protected void pushBarCode() {
        String s=mInputBarCode.getText().toString();
        if (s!=null)mBarCode=s;
        trySendRequest();
        mPushCode.setVisibility(View.INVISIBLE);
        mInputBarCode.setEnabled(false);
    }

    protected void inputBarCode() {
        mPushCode.setVisibility(View.VISIBLE);
        mInputBarCode.setEnabled(true);
    }
    private void trySendRequest(){
        if (mBarCode != null) {
            mSearchBarcodeRequest = new ProductSearch(mBarCode, "777");
            getSpiceManager().execute(mSearchBarcodeRequest, "searchBarcode", DurationInMillis.ONE_MINUTE, new ListContributorRequestListener());
        }
    }

}

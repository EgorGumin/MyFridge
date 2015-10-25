package com.lymno.myfridge.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.lymno.myfridge.R;
import com.lymno.myfridge.barcode_scanner.ScannerFragmentActivity;
import com.lymno.myfridge.database.DBHelper;
import com.lymno.myfridge.database.UserProductsDatabase;
import com.lymno.myfridge.model.ProductSearchResult;
import com.lymno.myfridge.model.UserProduct;
import com.lymno.myfridge.model.UserProductId;
import com.lymno.myfridge.network.BaseSampleSpiceFragment;
import com.lymno.myfridge.network.request.PushProduct;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Andre on 24.10.2015.
 */
public class FragmentInPutDataCount extends BaseSampleSpiceFragment {


    private String mBarCode = null;
    private ProductSearchResult mProdGote=null;

    @Bind(R.id.input_count)
    protected EditText mInputCount;
    @Bind(R.id.input_didline)
    protected CalendarView mInputDidLine;


    public static FragmentInPutDataCount create(String barCode, ProductSearchResult result) {
        FragmentInPutDataCount fragmentInPutDataCount = new FragmentInPutDataCount();
        fragmentInPutDataCount.mBarCode = barCode;
        fragmentInPutDataCount.mProdGote=result;
        return fragmentInPutDataCount;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflated=inflater.inflate(R.layout.input_data_count_fragment,container,false);
        ButterKnife.bind(inflated);
        mInputDidLine=(CalendarView)inflated.findViewById(R.id.input_didline);
        mInputCount=(EditText)inflated.findViewById(R.id.input_count);
        inflated.findViewById(R.id.push).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                push();
            }
        });
        return inflated;
    }

    private void push(){
        String date=DateFormatUtils.format(new Date(mInputDidLine.getDate()), "yyyyMMdd");
        PushProduct pushProduct=new PushProduct(((Integer)mProdGote.getProductID()).toString(),mInputCount.getText().toString()
                ,date);
        getSpiceManager().execute(pushProduct,"addProduct",  DurationInMillis.ONE_MINUTE, new ListContributorRequestListener());
    }


    public final class ListContributorRequestListener implements RequestListener<UserProductId> {
        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(getActivity(), "Failure: " + spiceException.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(UserProductId userProduct) {
            Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
            getActivity().finish();
        }

    }
}

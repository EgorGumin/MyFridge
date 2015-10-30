package com.lymno.myfridge.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.lymno.myfridge.Categories;
import com.lymno.myfridge.Measures;
import com.lymno.myfridge.R;
import com.lymno.myfridge.database.UserProductsDatabase;
import com.lymno.myfridge.model.UserProduct;
import com.lymno.myfridge.model.UserProductId;
import com.lymno.myfridge.network.BaseSampleSpiceFragment;
import com.lymno.myfridge.network.request.NewPushProd;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Andre on 25.10.2015.
 */
public class FragmentInputProduct  extends BaseSampleSpiceFragment {

    @Bind(R.id.input_count)
    protected EditText mInputCount;
    @Bind(R.id.input_didline)
    protected CalendarView mInputDidLine;
    protected EditText mInputName;
    protected Spinner mInputCategory;
    protected EditText mInputCountInPacket;
    protected Spinner mUnitMeasure;

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
        mInputName=(EditText)inflated.findViewById(R.id.input_name);
        mInputCount=(EditText)inflated.findViewById(R.id.input_count);
        mInputDidLine=(CalendarView)inflated.findViewById(R.id.input_didline);
        mInputCategory=(Spinner)inflated.findViewById(R.id.input_category);
        mInputCountInPacket=(EditText)inflated.findViewById(R.id.input_count_in_packet);
        mUnitMeasure=(Spinner)inflated.findViewById(R.id.input_unit_measure);

        mInputCategory.setAdapter(new ArrayAdapter<>(this.getContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, Categories.get()));
        mUnitMeasure.setAdapter(new ArrayAdapter<>(this.getContext(),
                android.R.layout.simple_list_item_1, android.R.id.text1, Measures.get()));
        return inflated;
    }

    private void push(){
        String date= DateFormatUtils.format(new Date(mInputDidLine.getDate()), "yyyyMMdd");
        NewPushProd newPushProd;
        newPushProd =new NewPushProd(mBarCode,""+(mInputCategory.getSelectedItemPosition()+1),
                mInputName.getText().toString(),mInputCountInPacket.getText().toString(),
                ""+mUnitMeasure.getSelectedItemPosition()+1,
                mInputCount.getText().toString(),date);
        getSpiceManager().execute(newPushProd, "addProduct", DurationInMillis.ONE_MINUTE, new ListContributorRequestListener());
    }

    public final class ListContributorRequestListener implements RequestListener<UserProductId> {
        @Override
        public void onRequestFailure(SpiceException spiceException) {
//            Toast.makeText(getActivity(), "Failure: " + spiceException.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(UserProductId userProduct) {
//            Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
//            String date=DateFormatUtils.format(new Date(mInputDidLine.getDate()), "yyyyMMdd");
//            UserProduct userProduct1=new UserProduct(userProduct.getId(),
//                    1,userProduct.getBaseProductID(),(mInputCategory.getSelectedItemPosition()+1)
//                    ,mInputName.getText().toString()
//                    ,+mUnitMeasure.getSelectedItemPosition()+1
//                    ,Integer.valueOf(mInputCount.getText().toString())
//                    ,Integer.valueOf(mInputCountInPacket.getText().toString())
//                    ,new Date(mInputDidLine.getDate()));
//            UserProductsDatabase.addUserProduct(userProduct1);
//            getActivity().finish();


        }

    }
}

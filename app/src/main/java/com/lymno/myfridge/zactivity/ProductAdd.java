package com.lymno.myfridge.zactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.lymno.myfridge.R;
import com.lymno.myfridge.model.Food;
import com.lymno.myfridge.network.BaseSampleSpiceActivity;
import com.lymno.myfridge.network.request.TestRequest;
import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

public class ProductAdd extends BaseSampleSpiceActivity {
    TestRequest test;
    TextView barcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_product_add);
        Intent intent = getIntent();
        String code = intent.getStringExtra("barcode");
        barcode = (TextView) findViewById(R.id.barcode);
        barcode.setText(code);

        test = new TestRequest(code);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getSpiceManager().execute(test, "test", DurationInMillis.ONE_MINUTE, new ListContributorRequestListener());
    }

    private void updateContributors(final Food result) {
        barcode.setText(result.getName() + result.getDescription());
    }

    public final class ListContributorRequestListener implements RequestListener<Food> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(ProductAdd.this, "failure", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(final Food result) {
            Toast.makeText(ProductAdd.this, "success", Toast.LENGTH_SHORT).show();
            if (result != null){
                updateContributors(result);
            }
            else{
                Toast.makeText(ProductAdd.this, "Неправильный тип кода или такого продукта еще нет в базе.", Toast.LENGTH_LONG).show();
            }

        }
    }
}

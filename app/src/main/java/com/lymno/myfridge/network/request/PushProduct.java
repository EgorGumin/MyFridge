package com.lymno.myfridge.network.request;

import com.lymno.myfridge.barcode_scanner.ScannerFragment;
import com.lymno.myfridge.model.ProductSearchResult;
import com.lymno.myfridge.model.UserProduct;
import com.lymno.myfridge.model.UserProductId;
import com.lymno.myfridge.network.Essen;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import java.util.Date;

import roboguice.util.temp.Ln;

public class PushProduct extends RetrofitSpiceRequest<UserProductId, Essen> {
    int id;
    int count;
    String date;

    public PushProduct(int id, int count, String date) {
        super(UserProductId.class, Essen.class);
        this.id = id;
        this.count = count;
        this.date = date;
    }


    @Override
    public UserProductId loadDataFromNetwork() {
        Ln.d("Call web service ");
        return getService().addProd(id, count, date, "4");
    }
}

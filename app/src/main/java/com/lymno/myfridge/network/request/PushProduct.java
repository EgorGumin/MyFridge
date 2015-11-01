//package com.lymno.myfridge.network.request;
//
//import com.lymno.myfridge.model.UserProductId;
//import com.lymno.myfridge.network.Api;
//import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;
//
//import roboguice.util.temp.Ln;
//
//public class PushProduct extends RetrofitSpiceRequest<UserProductId, Api> {
//    int id;
//    int count;
//    String date;
//
//    public PushProduct(int id, int count, String date) {
//        super(UserProductId.class, Api.class);
//        this.id = id;
//        this.count = count;
//        this.date = date;
//    }
//
//
//    @Override
//    public UserProductId loadDataFromNetwork() {
//        Ln.d("Call web service ");
//        return getService().addProd(id, count, date, "4");
//    }
//}

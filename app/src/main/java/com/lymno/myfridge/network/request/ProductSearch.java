package com.lymno.myfridge.network.request;

import com.lymno.myfridge.model.ProductSearchResult;
import com.lymno.myfridge.network.Api;
import com.lymno.myfridge.network.SampleRetrofitSpiceService;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import roboguice.util.temp.Ln;

//public class ProductSearch extends RetrofitSpiceRequest<ProductSearchResult, Api> {
//    private String code;
//    private String sessionID;
//
//
//    public ProductSearch(String code, String sessionID) {
//        super(ProductSearchResult.class, Api.class);
//        this.code = code;
//        this.sessionID = sessionID;
//    }
//
//    @Override
//    public ProductSearchResult loadDataFromNetwork() {
//        Ln.d("Call web service ");
//
//        return getService().searchBarcode(code, SampleRetrofitSpiceService.getSessionId());
//    }
//}

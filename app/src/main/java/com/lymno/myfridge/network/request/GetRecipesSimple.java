package com.lymno.myfridge.network.request;

import com.lymno.myfridge.model.Recipe;
import com.lymno.myfridge.network.Api;
import com.lymno.myfridge.network.SampleRetrofitSpiceService;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import roboguice.util.temp.Ln;

/**
 * Created by Egor on 25.10.2015.
 */
//public class GetRecipesSimple extends RetrofitSpiceRequest<Recipe.List, Api> {
//
//    public GetRecipesSimple() {
//        super(Recipe.List.class, Api.class);
//    }
//
//    @Override
//    public Recipe.List loadDataFromNetwork() {
//        Ln.d("Call web service ");
//
//        return getService().getRecipesSimple(SampleRetrofitSpiceService.getUserId());
//    }
//}
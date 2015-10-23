package com.lymno.myfridge.network;

import android.util.Log;

import java.io.IOException;


import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.client.UrlConnectionClient;

import java.net.HttpURLConnection.*;


/**
 * Created by Colored on 23.10.2015.
 */
public class InterceptingOkClient extends UrlConnectionClient
{
    public InterceptingOkClient()
    {
    }
//
//    public InterceptingOkClient(UrlConnectionClient client)
//    {
//        super(client);
//    }

    @Override
    public Response execute(Request request) throws IOException
    {
        Response response = super.execute(request);

        for (retrofit.client.Header header : response.getHeaders())
        {
            // do something with header
            Log.d("MYLOG", header.getValue());
        }

        return response;
    }

}

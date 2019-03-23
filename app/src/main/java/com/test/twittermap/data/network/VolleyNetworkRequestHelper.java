package com.test.twittermap.data.network;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


public class VolleyNetworkRequestHelper implements NetworkRequestHelper {

    private static VolleyNetworkRequestHelper mInstance;
    private RequestQueue mRequestQueue;

    private VolleyNetworkRequestHelper() {

    }

    public static synchronized VolleyNetworkRequestHelper getInstance() {
        if (mInstance == null) {
            mInstance = new VolleyNetworkRequestHelper();
        }
        return mInstance;
    }



    private RequestQueue getRequestQueue(Context mContext) {
        if(mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    @Override
    public void addRequest(Request mRequest, Context mContext) {
        getRequestQueue(mContext).add(mRequest);
    }
}

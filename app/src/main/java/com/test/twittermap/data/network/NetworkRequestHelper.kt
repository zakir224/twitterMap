package com.test.twittermap.data.network

import android.content.Context
import com.android.volley.Request

internal interface NetworkRequestHelper {

    fun addRequest(mRequest: Request<*>, mContext: Context)
}
package com.test.twittermap

import android.app.Application
import android.util.Log

import com.test.twittermap.data.AppDataManager
import com.test.twittermap.data.DataManager
import com.test.twittermap.data.network.VolleyNetworkRequestHelper
import com.twitter.sdk.android.core.DefaultLogger
import com.twitter.sdk.android.core.Twitter
import com.twitter.sdk.android.core.TwitterAuthConfig
import com.twitter.sdk.android.core.TwitterConfig

class TwitterMapApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}

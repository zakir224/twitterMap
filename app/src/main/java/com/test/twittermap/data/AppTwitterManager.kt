package com.test.twittermap.data


import com.test.twittermap.data.twitter.Api
import com.test.twittermap.data.twitter.TwitterSearchApi
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.models.Search
import com.twitter.sdk.android.core.services.params.Geocode


class AppTwitterManager private constructor() : TwitterManager {
    init {
        api = TwitterSearchApi()
    }

    override fun getTweets(query: String, lat: Double, lon: Double, radius: Int, distance: Geocode.Distance, searchCallback: Callback<Search>) {
        api.getTweets(query, lat, lon, radius, distance, searchCallback)
    }

    companion object {

        private lateinit var api: Api
        private var appTwitterManager: AppTwitterManager? = null

        val instance: AppTwitterManager
            get() {
                if (appTwitterManager == null) {
                    appTwitterManager = AppTwitterManager()
                }
                return appTwitterManager as AppTwitterManager
            }
    }
}

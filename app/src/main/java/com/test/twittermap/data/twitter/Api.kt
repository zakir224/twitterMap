package com.test.twittermap.data.twitter


import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.models.Search
import com.twitter.sdk.android.core.models.Tweet
import com.twitter.sdk.android.core.services.params.Geocode

import java.util.ArrayList

interface Api {
    fun getTweets(query: String, lat: Double, lon: Double, radius: Int, distance: Geocode.Distance, searchCallback: Callback<Search>)
}

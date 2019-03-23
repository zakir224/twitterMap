package com.test.twittermap.data.twitter


import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.TwitterApiClient
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.models.Search
import com.twitter.sdk.android.core.services.params.Geocode

class TwitterSearchApi : Api {

    private val twitterApiClient: TwitterApiClient = TwitterCore.getInstance().apiClient

    override fun getTweets(query: String, lat: Double, lon: Double, radius: Int,
                           distance: Geocode.Distance, searchCallback: Callback<Search>) {
        val searchService = twitterApiClient.searchService
        val tweetCall = searchService.tweets(query, Geocode(lat, lon, radius, distance),
                null, null, "mixed", 100, null, null, null, null)
        tweetCall.enqueue(searchCallback)
    }
}

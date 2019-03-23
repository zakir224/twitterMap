package com.test.twittermap.ui.map

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.test.twittermap.data.DataManager
import com.test.twittermap.data.TwitterManager
import com.test.twittermap.data.model.TweetMarker
import com.test.twittermap.data.model.TweetModelBuilder
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.models.Search
import com.twitter.sdk.android.core.models.Tweet
import com.twitter.sdk.android.core.services.params.Geocode

import java.util.ArrayList
import java.util.HashMap
import java.util.LinkedList
import java.util.Queue

import android.os.Handler

class MapPresenterContract<V : MapView>(private val twitterManager:
                                        TwitterManager, private val dataManager: DataManager) : MapPresenter<V> {

    private var mapView: MapView? = null
    private val tweetQueue: Queue<Tweet>
    val handler: Handler

    private var tweetRunnable: Runnable = object : Runnable {
        override fun run() {
            getTweets("montreal")
        }
    }

    private val dummyLatLng: ArrayList<LatLng>
        get() {
            val latLngs = ArrayList<LatLng>()
            latLngs.add(LatLng(37.413994, -122.110878))
            latLngs.add(LatLng(37.449094, -122.255382))
            latLngs.add(LatLng(37.236456, -121.539203))
            latLngs.add(LatLng(37.276263, -122.246857))
            return latLngs
        }

    init {
        tweetQueue = LinkedList()
        handler = Handler()
    }

    override fun onAttach(mvpView: V) {
        mapView = mvpView
    }

    override fun onMapReady() {
        mapView!!.updateLocation()
    }

    private fun getTweets(query: String) {
        if (mapView!!.lastKnownLocation() == null)
            return
        twitterManager.getTweets(query, mapView!!.lastKnownLocation()!!.latitude,
                mapView!!.lastKnownLocation()!!.longitude, dataManager.getPreferedRadius(),
                Geocode.Distance.KILOMETERS, object : Callback<Search>() {
            override fun success(result: Result<Search>) {
                processTweets(result)
            }

            override fun failure(exception: TwitterException) {
                // todo internet connection checking. skipped for this assignment
            }
        })
    }

    override fun onPause() {
        handler.removeCallbacks(tweetRunnable)
    }

    override fun onResume() {
        handler.post(tweetRunnable)
    }

    override fun radiusPickerClicked() {
        mapView!!.openRadiusPicker(dataManager.getPreferedRadius())
    }

    override fun onRadiusUpdated(radius: Int) {
        dataManager.setPreferedRadius(radius)
        handler.removeCallbacks(tweetRunnable)
        handler.post(tweetRunnable)
    }

    private fun processTweets(result: Result<Search>?) {
        updateTweetQueue(result!!.data!!.tweets)
        updateMap()
    }

    private fun updateMap() {
        val markerOptions = HashMap<String, TweetMarker>()
        val currentMarkers = mapView!!.mapMarkers()
        var marker: MarkerOptions
        for (tweet in tweetQueue) {
            if (tweet.user.geoEnabled && tweet.coordinates != null) {
                if (currentMarkers == null || !currentMarkers.containsKey(tweet.idStr)) {
                    marker = MarkerOptions().position(LatLng(tweet.coordinates.latitude!!, tweet.coordinates.longitude!!)).title(tweet.text)
                    markerOptions[tweet.idStr] = TweetMarker(marker, tweet)
                }
            }
        }
        if (currentMarkers != null && markerOptions.size + currentMarkers.size <= 100) {
            mapView!!.updateMap(markerOptions)
            handler.postDelayed(tweetRunnable, (10 * 1000).toLong())
        } else {
            mapView!!.refreshMap(markerOptions)
            handler.postDelayed(tweetRunnable, (30 * 1000).toLong())
        }
    }

    private fun updateTweetQueue(tweets: List<Tweet>) {
        if (tweetQueue.size >= 100) {
            removeOldTweets()
        }

        addNewTweets(tweets)
    }

    private fun addNewTweets(tweets: List<Tweet>) {
        var i = 0
        while (i < tweets.size && tweetQueue.size < 100) {
            if (tweets[i].coordinates != null) {
                tweetQueue.add(tweets[i])
            }
            i++
        }
    }

    private fun removeOldTweets() {
        for (i in 0..9) {
            tweetQueue.poll()
        }
    }

    override fun onDetailButtonClick(marker: TweetMarker?) {
        val tweet = marker!!.tweet
        val tweetModelBuilder = TweetModelBuilder()
                .setTweetId(tweet.getId())
                .setTweetText(tweet.text)
                .setTweetHandle(tweet.user.name)
                .setTweetAvatar(tweet.user.profileImageUrlHttps)
                .setTweetTime(tweet.createdAt)
        if (tweet.entities != null) {
            if (tweet.entities.media != null && tweet.entities.media.size > 0) {
                tweetModelBuilder.setTweetMediaType(tweet.entities.media[0].type)
                tweetModelBuilder.setTweetMediaType(tweet.entities.media[0].mediaUrl)
            }
        }

        mapView!!.openTweetDetail(tweetModelBuilder.build())
    }

    override fun onTextQuery(query: String) {
        getTweets(query)
    }

    override fun onLocationUnavailable() {

    }

    override fun onLocationUpdate(latLng: LatLng) {
        mapView!!.moveCameraMap(latLng.latitude, latLng.longitude)
        handler.removeCallbacks(tweetRunnable)
        handler.post(tweetRunnable)
    }

    override fun onMyLocationClick() {
        mapView!!.updateLocation()
    }
}

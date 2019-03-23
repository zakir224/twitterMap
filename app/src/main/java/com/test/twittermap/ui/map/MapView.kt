package com.test.twittermap.ui.map

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.test.twittermap.data.model.TweetMarker
import com.test.twittermap.data.model.TweetModel
import com.test.twittermap.ui.base.BaseMvpView

interface MapView : BaseMvpView {
    fun lastKnownLocation(): LatLng?
    fun mapMarkers(): MutableMap<String, TweetMarker>?

    fun refreshMap(markers: MutableMap<String, TweetMarker>)
    fun updateMap(markers: Map<String, TweetMarker>)
    fun moveCameraMap(lat: Double, lon: Double)
    fun updateLocation()
    fun openTweetDetail(tweet: TweetModel)
    fun openRadiusPicker(currentValue: Int)
}

package com.test.twittermap.ui.map

import com.google.android.gms.maps.model.LatLng
import com.test.twittermap.data.model.TweetMarker
import com.test.twittermap.ui.base.BaseMvpPresenter

interface MapPresenter<V : MapView> : BaseMvpPresenter<V> {
    fun onMapReady()
    fun onDetailButtonClick(marker: TweetMarker?)
    fun onTextQuery(query: String)
    fun onLocationUnavailable()
    fun onLocationUpdate(latLng: LatLng)
    fun onMyLocationClick()
    fun onPause()
    fun onResume()
    fun radiusPickerClicked()
    fun onRadiusUpdated(radius: Int)
}

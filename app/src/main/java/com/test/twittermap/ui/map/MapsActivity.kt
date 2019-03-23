package com.test.twittermap.ui.map

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.NumberPicker
import android.widget.TextView

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.test.twittermap.R
import com.test.twittermap.data.AppDataManager
import com.test.twittermap.data.AppTwitterManager
import com.test.twittermap.data.model.TweetMarker
import com.test.twittermap.data.model.TweetModel
import com.test.twittermap.data.preference.AppPreferenceHelper
import com.test.twittermap.ui.base.BaseActivity
import com.test.twittermap.ui.twitter.TwitterDetailActivity
import com.test.twittermap.util.DialogUtil
import java.util.concurrent.Executors


class MapsActivity : BaseActivity(), OnMapReadyCallback, MapView, GoogleMap.OnMyLocationClickListener, GoogleMap.OnMyLocationButtonClickListener {

    override fun lastKnownLocation(): LatLng? {
        return lastKnownLocation
    }

    override fun mapMarkers(): MutableMap<String, TweetMarker>?{
        return markerOptions
    }


    private var mFusedLocationClient: FusedLocationProviderClient? = null
    private var mapPresenter: MapPresenter<MapView>? = null
    private var lastKnownLocation: LatLng? = null
    private var mMap: GoogleMap? = null
    private var markerOptions: MutableMap<String, TweetMarker>? = null

    val customMarker: GoogleMap.InfoWindowAdapter
        get() = object : GoogleMap.InfoWindowAdapter {
            override fun getInfoWindow(marker: Marker): View {
                val myContentView = layoutInflater.inflate(
                        R.layout.map_info_window, null)
                myContentView.setBackgroundColor(Color.WHITE)
                val tvTitle = myContentView.findViewById<TextView>(R.id.tvMarkerText)
                tvTitle.text = marker.title

                return myContentView
            }

            override fun getInfoContents(marker: Marker): View? {
                return null
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps2)
        setUp()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap!!.setOnMyLocationClickListener(this)
        mMap!!.setOnMyLocationButtonClickListener(this)
        mMap!!.setInfoWindowAdapter(customMarker)
        mapPresenter!!.onMapReady()
    }

    @SuppressLint("MissingPermission")
    override fun updateLocation() {
        if (!hasLocationPermissions()) {
            mapPresenter!!.onLocationUnavailable()
            return
        }
        mFusedLocationClient!!.lastLocation.addOnSuccessListener(this@MapsActivity) {
            lastKnownLocation = LatLng(it.latitude, it.longitude)
            mapPresenter!!.onLocationUpdate(lastKnownLocation as LatLng)
        }
        mMap!!.isMyLocationEnabled = true
        mMap!!.uiSettings.isMyLocationButtonEnabled = true
        mMap!!.uiSettings.isZoomControlsEnabled = true
        mMap!!.uiSettings.isZoomGesturesEnabled = true
        mMap!!.setOnInfoWindowClickListener { marker ->
            mapPresenter!!.onDetailButtonClick(markerOptions!![marker.tag])
        }
    }

    override fun setUp() {
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val mapFragment = (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?)!!
        mapFragment.getMapAsync(this)
        mapPresenter = MapPresenterContract(AppTwitterManager.instance,
                AppDataManager.getInstance(AppPreferenceHelper.getInstance(this)))
        mapPresenter!!.onAttach(this)
    }

    override fun refreshMap(markerOptions: MutableMap<String, TweetMarker>) {
        mMap!!.clear()
        this.markerOptions = markerOptions
        for ((key, value) in this.markerOptions!!) {
            mMap!!.addMarker(value.markerOptions).tag = key
        }
    }

    override fun updateMap(markers: Map<String, TweetMarker>) {
        this.markerOptions!!.putAll(markers)
        for ((key, value) in markers) {
            mMap!!.addMarker(value.markerOptions).tag = key
        }
    }

    override fun moveCameraMap(lat: Double, lon: Double) {
        mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(lat, lon), 6.0f))
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        mapPresenter!!.onMapReady()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_map, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val radiusItem = menu.findItem(R.id.action_radius_picker)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
//                if (!searchView.isIconified) {
//                    searchView.isIconified = true
//                }
//                searchItem.collapseActionView()
                hideKeyBoard()
                mapPresenter!!.onTextQuery(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }

        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun openRadiusPicker(currentValue: Int) {
        showRadiusPicker(currentValue)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_radius_picker) {
            mapPresenter!!.radiusPickerClicked()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onMyLocationButtonClick(): Boolean {
        mapPresenter!!.onMyLocationClick()
        return true
    }

    override fun onMyLocationClick(location: Location) {
        mapPresenter!!.onMyLocationClick()
    }

    override fun onResume() {
        super.onResume()
        mapPresenter!!.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapPresenter!!.onPause()
    }

    override fun openTweetDetail(tweet: TweetModel) {
        val tweetDetailIntent = Intent(this@MapsActivity, TwitterDetailActivity::class.java)
        tweetDetailIntent.putExtra(TwitterDetailActivity.TWEET, tweet)
        startActivity(tweetDetailIntent)
    }

    private fun showRadiusPicker(currentValue: Int) {
        val picker = NumberPicker(this)
        picker.minValue = 5
        picker.maxValue = 5000
        picker.value = currentValue

        val layout = FrameLayout(this)
        layout.addView(picker, FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT,
                Gravity.CENTER))

        AlertDialog.Builder(this)
                .setView(layout)
                .setPositiveButton(android.R.string.ok) { dialogInterface, i -> mapPresenter!!.onRadiusUpdated(picker.value) }
                .setNegativeButton(android.R.string.cancel, null)
                .show()

    }
}

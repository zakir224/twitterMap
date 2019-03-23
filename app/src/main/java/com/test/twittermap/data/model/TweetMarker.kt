package com.test.twittermap.data.model

import com.google.android.gms.maps.model.MarkerOptions
import com.twitter.sdk.android.core.models.Tweet

data class TweetMarker(var markerOptions: MarkerOptions, var tweet: Tweet)

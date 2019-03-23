package com.test.twittermap.util

import android.support.annotation.IntegerRes
import com.google.android.gms.common.util.Clock
import com.test.twittermap.crypto.signature.HmacSha1Signature
import com.test.twittermap.data.twitter.Config
import com.test.twittermap.data.twitter.OAuth
import com.test.twittermap.data.twitter.OAuthBuilder
import com.test.twittermap.data.twitter.OAuthManager
import org.json.JSONException
import org.json.JSONObject
import org.junit.Test

import org.junit.Assert.*
import org.junit.BeforeClass
import java.lang.System.currentTimeMillis
import java.security.Timestamp
import java.time.LocalDateTime
import java.util.*




class StringUtilTest {


    @Test
    fun getEncodedString() {
        val encodedString = StringUtil.getEncodedString("tnnArxj06cWHq44gCs1OSKk/jLY=")
        assertEquals("tnnArxj06cWHq44gCs1OSKk%2FjLY%3D",encodedString)
        var i = 0
    }

    @Test
    fun getRandom() {

    }
}
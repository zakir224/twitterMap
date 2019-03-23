package com.test.twittermap.util


import android.util.Base64

import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.security.SecureRandom


object StringUtil {

    @Throws(UnsupportedEncodingException::class)
    fun getEncodedString(s: String?): String {
        if (s == null) {
            return ""
        }
        try {
            return URLEncoder.encode(s, StandardCharsets.UTF_8.toString())
                    // OAuth encodes some characters differently:
                    .replace("+", "%20").replace("*", "%2A")
                    .replace("%7E", "~")
            // This could be done faster with more hand-crafted code.
        } catch (wow: UnsupportedEncodingException) {
            throw RuntimeException(wow.message, wow)
        }

    }


    fun getRandom(numOfBytes: Int): String {
        val random = SecureRandom()
        val bytes = ByteArray(numOfBytes)
        random.nextBytes(bytes)
        return String(Base64.encode(bytes, Base64.NO_WRAP)).replace("[^A-Za-z0-9]".toRegex(), "")
    }
}

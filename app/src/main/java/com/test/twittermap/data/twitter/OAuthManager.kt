package com.test.twittermap.data.twitter

import com.test.twittermap.crypto.signature.Signature
import com.test.twittermap.util.StringUtil


import java.io.UnsupportedEncodingException
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import java.util.ArrayList
import java.util.Collections
import java.util.HashMap

class OAuthManager(var mConfig: Config, var oAuth: OAuth, private val signature: Signature) {

    val signatureBaseString: String
        @Throws(UnsupportedEncodingException::class)
        get() = "$configString&$parameterString"

    private val configString: String
        @Throws(UnsupportedEncodingException::class)
        get() = mConfig.encodedConfig

    private val parameterString: String
        @Throws(UnsupportedEncodingException::class)
        get() {
            val encodeKeyValues = HashMap<String, String>()
            encodeKeyValues.putAll(oAuthParams)
            encodeKeyValues.putAll(configParams)
            val sortedParamString = createSortedParamString(encodeKeyValues)
            return StringUtil.getEncodedString(sortedParamString)
        }

    private val configParams: Map<String, String>
        get() = mConfig.encodedConfigParams

    private val oAuthParams: HashMap<String, String>
        get() = oAuth.getEncodedKeyValuePair()

    val oAuthHeaders: String
        get() = oAuth.getHeaders()

    @Throws(UnsupportedEncodingException::class, InvalidKeyException::class, NoSuchAlgorithmException::class)
    fun getSignature(key: String): String {
        val signatureBaseString = signatureBaseString
        return signature.sign(signatureBaseString, key)
    }

    private fun createSortedParamString(encodeKeyValues: Map<String, String>): String {
        val keyHashSet = ArrayList(encodeKeyValues.keys)
        keyHashSet.sort()
        val stringBuilder = StringBuilder()
        for (key in keyHashSet) {
            stringBuilder.append(key).append("=").append(encodeKeyValues[key]).append("&")
        }
        return stringBuilder.substring(0, stringBuilder.length - 1)
    }

}

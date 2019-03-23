package com.test.twittermap.data.twitter

import com.test.twittermap.util.StringUtil
import java.util.*
import kotlin.collections.HashMap

class OAuth(var token: String?,
                 var consumerKey: String?,
                 var nonce: String?,
                 var signature: String?,
                 var signatureMethod: String?,
                 var timeStamp: String?,
                 var version: String?) {

    fun getEncodedKeyValuePair() : HashMap<String, String> {
        val encodedKeyValuePairs = java.util.LinkedHashMap<String, String>()
        encodedKeyValuePairs[StringUtil.getEncodedString("oauth_consumer_key")] = StringUtil.getEncodedString(consumerKey)
        encodedKeyValuePairs[StringUtil.getEncodedString("oauth_nonce")] = StringUtil.getEncodedString(nonce)
        encodedKeyValuePairs[StringUtil.getEncodedString("oauth_token")] = StringUtil.getEncodedString(token)
        encodedKeyValuePairs[StringUtil.getEncodedString("oauth_signature_method")] = StringUtil.getEncodedString(signatureMethod)
        encodedKeyValuePairs[StringUtil.getEncodedString("oauth_timestamp")] = StringUtil.getEncodedString(timeStamp)
        encodedKeyValuePairs[StringUtil.getEncodedString("oauth_version")] = StringUtil.getEncodedString(version)
        if (signature != null && !signature!!.isEmpty()) {
            encodedKeyValuePairs[StringUtil.getEncodedString("oauth_signature")] = StringUtil.getEncodedString(signature)
        }
        return encodedKeyValuePairs
    }

    fun getHeaders(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("OAuth ")
        for (item in getEncodedKeyValuePair().entries) {
            val key = item.key
            val value = item.value
            stringBuilder.append(key).append("=").append("\"")
                    .append(value).append("\"").append(",")
        }
        var parameterString = stringBuilder.toString()
        parameterString = parameterString.replace(",$".toRegex(), "")
        return parameterString
    }
}

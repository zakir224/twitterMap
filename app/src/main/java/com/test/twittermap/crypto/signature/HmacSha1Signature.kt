package com.test.twittermap.crypto.signature

import android.util.Base64

import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

class HmacSha1Signature : Signature {

    @Throws(NoSuchAlgorithmException::class, InvalidKeyException::class)
    override fun sign(data: String, key: String): String {
        val signingKey = SecretKeySpec(key.toByteArray(StandardCharsets.UTF_8), HMAC_SHA1_ALGORITHM)
        val mac = Mac.getInstance(HMAC_SHA1_ALGORITHM)
        mac.init(signingKey)
        val aFinal = mac.doFinal(data.toByteArray(StandardCharsets.UTF_8))
        return String(Base64.encode(aFinal, Base64.NO_WRAP), StandardCharsets.UTF_8)
    }

    companion object {
        private val HMAC_SHA1_ALGORITHM = "HmacSHA1"
    }
}

package com.test.twittermap.crypto.signature

import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException

interface Signature {
    @Throws(NoSuchAlgorithmException::class, InvalidKeyException::class)
    fun sign(data: String, key: String): String
}

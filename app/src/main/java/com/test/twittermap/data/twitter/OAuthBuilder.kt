package com.test.twittermap.data.twitter

class OAuthBuilder {
    private var consumerKey: String? = null
    private var nonce: String? = null
    private var signature: String? = null
    private var signatureMethod: String? = null
    private var timeStamp: String? = null
    private var token: String? = null
    private var version: String? = null

    fun setNonce(nonce: String): OAuthBuilder {
        this.nonce = nonce
        return this
    }

    fun setSignature(signature: String): OAuthBuilder {
        this.signature = signature
        return this
    }

    fun setSignatureMethod(signatureMethod: String): OAuthBuilder {
        this.signatureMethod = signatureMethod
        return this
    }

    fun setTimeStamp(timeStamp: String): OAuthBuilder {
        this.timeStamp = timeStamp
        return this
    }

    fun setToken(token: String): OAuthBuilder {
        this.token = token
        return this
    }

    fun setVersion(version: String): OAuthBuilder {
        this.version = version
        return this
    }

    fun setConsumerKey(consumerKey: String): OAuthBuilder {
        this.consumerKey = consumerKey
        return this
    }

    fun build(): OAuth {
        return OAuth(token, consumerKey,
                nonce, signature, signatureMethod, timeStamp, version);
    }

}

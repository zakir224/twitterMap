package com.test.twittermap.data.model

class TweetModelBuilder {

    private var tweetText: String? = null
    private var tweetId: Long = 0
    private var tweetHandle: String? = null
    private var tweetMediaUrl: String? = null
    private var tweetAvatar: String? = null
    private var tweetTime: String? = null
    private var tweetMediaType: String? = null

    fun setTweetText(tweetText: String): TweetModelBuilder {
        this.tweetText = tweetText
        return this
    }

    fun setTweetId(tweetId: Long): TweetModelBuilder {
        this.tweetId = tweetId
        return this
    }

    fun setTweetHandle(tweetHandle: String): TweetModelBuilder {
        this.tweetHandle = tweetHandle
        return this
    }

    fun setTweetMediaUrl(tweetMediaUrl: String): TweetModelBuilder {
        this.tweetMediaUrl = tweetMediaUrl
        return this
    }

    fun setTweetAvatar(tweetAvatar: String): TweetModelBuilder {
        this.tweetAvatar = tweetAvatar
        return this
    }

    fun setTweetTime(tweetTime: String): TweetModelBuilder {
        this.tweetTime = tweetTime
        return this
    }

    fun build(): TweetModel {
        return TweetModel(tweetText!!, tweetId, tweetMediaType,
                tweetHandle!!, tweetMediaUrl,
                tweetAvatar, tweetTime!!)
    }

    fun setTweetMediaType(tweetMediaType: String): TweetModelBuilder {
        this.tweetMediaType = tweetMediaType
        return this
    }
}

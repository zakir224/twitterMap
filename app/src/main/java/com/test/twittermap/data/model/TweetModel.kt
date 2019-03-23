package com.test.twittermap.data.model

import java.io.Serializable

data class TweetModel(var mTweetText: String, val mTweetId: Long,
                      val mTweetMediaType: String?,
                      var mTweetHandle: String, var mTweetMediaUrl: String?,
                      var tweetAvatar: String?, var tweetTime: String): Serializable
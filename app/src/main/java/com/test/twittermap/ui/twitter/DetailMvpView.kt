package com.test.twittermap.ui.twitter

import com.test.twittermap.data.model.TweetModel
import com.test.twittermap.ui.base.BaseMvpView

interface DetailMvpView : BaseMvpView {

    fun getTweetFromIntent(): TweetModel
    fun initViews(tweet: TweetModel)
    fun openImage(url: String)
    fun openVideo(url: String)
}

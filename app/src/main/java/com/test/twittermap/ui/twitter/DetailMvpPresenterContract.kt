package com.test.twittermap.ui.twitter

import com.test.twittermap.data.model.TweetModel

import java.util.Objects

class DetailMvpPresenterContract<V : DetailMvpView> : DetailMvpPresenter<V> {

    private lateinit var detailMvpView: DetailMvpView
    private var tweetFromIntent: TweetModel? = null

    override fun onMediaClicked() {
        if (Objects.requireNonNull<String>(tweetFromIntent!!.mTweetMediaType) == "photo") {
            detailMvpView.openImage(tweetFromIntent!!.mTweetMediaUrl!!)
        } else if (Objects.requireNonNull<String>(tweetFromIntent!!.mTweetMediaType) == "video") {
            detailMvpView.openVideo(tweetFromIntent!!.mTweetMediaUrl!!)
        }
    }

    override fun onAttach(mvpView: V) {
        detailMvpView = mvpView
        tweetFromIntent = detailMvpView.getTweetFromIntent()
        detailMvpView.initViews(tweetFromIntent!!)
    }
}

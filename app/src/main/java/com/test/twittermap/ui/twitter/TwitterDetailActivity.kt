package com.test.twittermap.ui.twitter

import android.content.Intent
import android.net.Uri
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide
import com.test.twittermap.R
import com.test.twittermap.data.model.TweetModel
import com.test.twittermap.ui.base.BaseActivity
import com.twitter.sdk.android.core.models.Tweet

class TwitterDetailActivity : BaseActivity(), DetailMvpView {

    private var mHandle: TextView? = null
    private var mText: TextView? = null
    private var mTime: TextView? = null
    private var mImgView: ImageView? = null
    private val mRetweet: Button? = null
    private val mLike: TextView? = null
    private var mImgViewProfile: ImageView? = null

    private var mvpPresenter: DetailMvpPresenter<DetailMvpView>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_twitter_detail)

        setUp()
    }

    override fun getTweetFromIntent(): TweetModel {
        return intent.getSerializableExtra(TWEET) as TweetModel
    }

    override fun initViews(tweet: TweetModel) {
        mHandle!!.text = tweet.mTweetHandle
        mText!!.text = tweet.mTweetText
        mTime!!.text = tweet.tweetTime
        if (tweet.mTweetMediaUrl != null
                && !tweet.mTweetMediaUrl!!.isEmpty() && tweet.mTweetMediaType == "photo") {
            mImgView!!.visibility = View.VISIBLE
            mImgView!!.setOnClickListener { v -> mvpPresenter!!.onMediaClicked() }
            Glide.with(this).load(tweet.mTweetMediaUrl).into(mImgView!!)
        }
        if (tweet.tweetAvatar != null) {
            Glide.with(this).load(tweet.tweetAvatar).into(mImgViewProfile!!)
        }
    }

    override fun openImage(url: String) {
        val i = Intent(Intent.ACTION_VIEW)
        i.setDataAndType(Uri.parse(url), "image/*")
        startActivity(i)
    }

    override fun openVideo(url: String) {
        val i = Intent(Intent.ACTION_VIEW)
        i.setDataAndType(Uri.parse(url), "video/*")
        startActivity(i)
    }

    override fun setUp() {
        mHandle = findViewById(R.id.tvTwitterHandle)
        mText = findViewById(R.id.tvTwitterMessage)
        mTime = findViewById(R.id.tvTwitterTime)
        mImgView = findViewById(R.id.imgTwitterImage)
        mImgViewProfile = findViewById(R.id.imgProfile)
        mvpPresenter = DetailMvpPresenterContract()
        mvpPresenter!!.onAttach(this)
    }

    companion object {

        val TWEET = "tweet"
    }
}

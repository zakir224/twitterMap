package com.test.twittermap.ui.twitter

import com.test.twittermap.ui.base.BaseMvpPresenter

interface DetailMvpPresenter<V : DetailMvpView> : BaseMvpPresenter<V> {
    fun onMediaClicked()
}

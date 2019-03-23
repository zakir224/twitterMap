package com.test.twittermap.ui.base


interface BaseMvpPresenter<V : BaseMvpView> {

    fun onAttach(mvpView: V)
}

package com.test.twittermap.ui.base

import android.support.annotation.StringRes


interface BaseMvpView {

    fun hasPermission(permission: String): Boolean

    fun showLoading()

    fun hideLoading()

    fun hideKeyBoard()

    fun onError(@StringRes resId: Int)

    fun onError(message: String?)

    fun showMessage(message: String?)

    fun showMessage(@StringRes resId: Int)

    fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int)
}

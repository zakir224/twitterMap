package com.test.twittermap.ui.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.view.View

import com.test.twittermap.util.DialogUtil


abstract class BaseFragment : Fragment(), BaseMvpView {

    private lateinit var baseActivity: BaseActivity
    private var mProgressDialog: ProgressDialog? = null
    private val mValidPause: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp(view)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is BaseActivity) {
            this.baseActivity = context
        }
    }

    override fun showLoading() {
        hideLoading()
        mProgressDialog = DialogUtil.showLoadingDialog(baseActivity)
    }

    override fun hideLoading() {
        if (mProgressDialog!!.isShowing) {
            mProgressDialog!!.cancel()
        }
    }

    override fun onError(message: String?) {
        baseActivity.onError(message)
    }

    override fun onError(@StringRes resId: Int) {
        baseActivity.onError(resId)
    }

    override fun showMessage(message: String?) {
        baseActivity.showMessage(message)
    }

    override fun showMessage(@StringRes resId: Int) {
        baseActivity.showMessage(resId)
    }



    protected abstract fun setUp(view: View)

    interface Callback {
        fun onFragmentAttached()

        fun onFragmentDetached(tag: String)
    }

    override fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        baseActivity.requestPermissionsSafely(permissions, requestCode)
    }

    override fun hasPermission(permission: String): Boolean {
        return baseActivity.hasPermission(permission)
    }
}

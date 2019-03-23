package com.test.twittermap.ui.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.View


abstract class BaseDialogFragment : DialogFragment() {

    lateinit var baseActivity: BaseActivity
    private val mProgressDialog: ProgressDialog? = null

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

    override fun onDetach() {
        super.onDetach()
    }


    protected abstract fun setUp(view: View)

    override fun onDestroy() {
        super.onDestroy()
    }
}

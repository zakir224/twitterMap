package com.test.twittermap.util

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.NumberPicker

import com.test.twittermap.R


/**
 * Created by zakir on 31/12/2018.
 */

object DialogUtil {

    fun showAlert(title: String, message: String, context: Context) {
        val dialog = AlertDialog.Builder(context)
        dialog.setCancelable(false)
        dialog.setTitle(title)
        dialog.setMessage(message)
        dialog.setPositiveButton(R.string.okay) { dialog1, id -> dialog1.dismiss() }
        dialog.create().show()
    }

    fun showLoadingDialog(context: Context): ProgressDialog {
        val progressDialog = ProgressDialog(context)
        progressDialog.show()
        if (progressDialog.window != null) {
            progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        progressDialog.setContentView(R.layout.progress_dialog)
        progressDialog.isIndeterminate = true
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)
        return progressDialog
    }
}

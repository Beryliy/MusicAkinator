package com.fourcore.musicakinator.presentation.dialog

import android.app.Activity
import android.app.Dialog
import com.fourcore.musicakinator.R

class ProgressDialog{
    var dialog: Dialog? = null

    fun show(activity: Activity) {
        if(dialog == null) {
            dialog = Dialog(activity, R.style.ProgressDialog)
        }
        val view = activity.layoutInflater.inflate(R.layout.dialog_progress, null)
        dialog?.setContentView(view)
        dialog?.show()
    }

    fun hide() {
        dialog?.dismiss()
    }

    fun destroy() {
        dialog?.dismiss()
        dialog = null
    }
}
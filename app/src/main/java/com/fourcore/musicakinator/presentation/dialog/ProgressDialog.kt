package com.fourcore.musicakinator.presentation.dialog

import android.app.Activity
import android.app.AlertDialog
import com.fourcore.musicakinator.R

class ProgressDialog {
    var alertDialog: AlertDialog? = null

    fun show(activity: Activity) {
        val view = activity.layoutInflater.inflate(R.layout.dialog_progress, null)
        if(alertDialog == null) {
            alertDialog = AlertDialog.Builder(activity)
                .setView(view)
                .setCancelable(false)
                .create()
        }
        alertDialog?.show()
    }

    fun hide() {
        alertDialog?.dismiss()
    }
}
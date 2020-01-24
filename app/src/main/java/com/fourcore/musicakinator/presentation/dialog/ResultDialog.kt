package com.fourcore.musicakinator.presentation.dialog

import android.app.Activity
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.fourcore.musicakinator.R

class ResultDialog(
    @DrawableRes val image: Int,
    @StringRes val description: Int
) {
    fun show(activity: Activity) {
        val builder = AlertDialog.Builder(activity, R.style.ResultDialog)
        val view = activity.layoutInflater.inflate(R.layout.dialog_result, null)
        view.findViewById<ImageView>(R.id.resultIv).setImageResource(image)
        view.findViewById<TextView>(R.id.resultTv).setText(description)
        builder.setView(view).setCancelable(true).show()
    }
}
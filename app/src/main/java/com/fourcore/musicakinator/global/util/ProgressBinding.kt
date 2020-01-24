package com.fourcore.musicakinator.global.util

import android.widget.ProgressBar
import androidx.databinding.BindingAdapter

object ProgressBinding{
    @BindingAdapter("android:progress")
    @JvmStatic
    fun bindProgress(progressBar: ProgressBar, progress: Int) {
        progressBar.setProgress(progress)
    }
}
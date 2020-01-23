package com.fourcore.musicakinator.global.util

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.setImage(url: String) {
    Glide.with(this).load(url).into(this)
}
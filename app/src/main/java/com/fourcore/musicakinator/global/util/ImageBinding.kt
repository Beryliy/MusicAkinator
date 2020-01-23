package com.fourcore.musicakinator.global.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageBinding{
    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun setImageByURL(imageView: ImageView, url: String?) {
        Glide.with(imageView).load(url).into(imageView)
    }
}
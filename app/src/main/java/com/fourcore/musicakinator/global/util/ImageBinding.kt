package com.fourcore.musicakinator.global.util

import android.widget.ImageButton
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object ImageBinding{
    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun setImageByURL(imageView: ImageView, url: String?) {
        Glide.with(imageView).load(url).into(imageView)
    }

    @BindingAdapter("android:src")
    @JvmStatic
    fun setImageByResource(imageButton: ImageButton, @DrawableRes resource: Int) {
        imageButton.setImageResource(resource)
    }
}
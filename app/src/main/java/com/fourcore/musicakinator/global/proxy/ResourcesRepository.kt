package com.fourcore.musicakinator.global.proxy

import android.content.Context
import android.content.res.Resources
import androidx.annotation.StringRes

class ResourcesRepository(context: Context) {
    private val resources: Resources = context.resources

    fun getString(@StringRes stringId: Int) = resources.getString(stringId)
}
package com.fourcore.musicakinator.global.proxy

import android.content.Context
import android.content.res.Resources
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourcesRepository @Inject constructor(context: Context) {
    val resources: Resources = context.resources

    fun getString(@StringRes stringId: Int) = resources.getString(stringId)

    fun getString(@StringRes stringId: Int, vararg params: Any) = resources.getString(stringId, *params)

    fun getInteger(@IntegerRes integerId: Int) = resources.getInteger(integerId)
}
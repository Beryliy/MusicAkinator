package com.fourcore.musicakinator.di.module

import android.app.Application
import android.content.Context
import com.deezer.sdk.network.connect.DeezerConnect
import com.fourcore.musicakinator.R
import com.fourcore.musicakinator.global.proxy.ResourcesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object ApiModule {
    @Provides
    @Singleton
    @JvmStatic
    fun provideDeezerConnect(
        context: Context,
        resourcesRepository: ResourcesRepository
    ) = DeezerConnect(context, resourcesRepository.getString(R.string.applicationId))
}
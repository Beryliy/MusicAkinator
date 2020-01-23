package com.fourcore.musicakinator.di.module

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
        resourcesRepository: ResourcesRepository
    ) = resourcesRepository.getString(R.string.applicationId)
}
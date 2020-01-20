package com.fourcore.musicakinator.di.component

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppProvideModule {
    @Singleton
    @JvmStatic
    @Provides
    fun provideContext(application: Application): Context = application
}
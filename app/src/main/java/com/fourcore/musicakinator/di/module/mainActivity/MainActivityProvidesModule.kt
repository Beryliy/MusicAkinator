package com.fourcore.musicakinator.di.module.mainActivity

import com.fourcore.musicakinator.di.ActivityScope
import com.fourcore.musicakinator.presentation.dialog.ProgressDialog
import dagger.Module
import dagger.Provides

@Module
object MainActivityProvidesModule {
    @Provides
    @ActivityScope
    @JvmStatic
    fun provideProgressDialog() = ProgressDialog()
}
package com.fourcore.musicakinator.di.builders

import com.fourcore.musicakinator.di.module.MainActivityBindsModule
import com.fourcore.musicakinator.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [
        MainActivityBindsModule::class
    ])
    abstract fun contributeStartActivity(): MainActivity
}
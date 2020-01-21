package com.fourcore.musicakinator.di.builders

import com.fourcore.musicakinator.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun contributeStartActivity(): MainActivity
}
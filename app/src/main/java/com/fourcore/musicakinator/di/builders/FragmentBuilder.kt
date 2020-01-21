package com.fourcore.musicakinator.di.builders

import com.fourcore.musicakinator.di.FragmentScope
import com.fourcore.musicakinator.di.module.lyricRecogniser.LyricRecogniserBindsModule
import com.fourcore.musicakinator.di.module.lyricRecogniser.LyricRecogniserProvidesModule
import com.fourcore.musicakinator.presentation.lyricRecogniser.LyricRecogniserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {
    @ContributesAndroidInjector(modules = [
        LyricRecogniserBindsModule::class,
        LyricRecogniserProvidesModule::class
    ])
    @FragmentScope
    abstract fun contributeLyricRecogniserFragment(): LyricRecogniserFragment
}
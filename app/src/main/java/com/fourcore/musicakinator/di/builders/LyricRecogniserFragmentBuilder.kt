package com.fourcore.musicakinator.di.builders

import com.fourcore.musicakinator.presentation.lyricRecogniser.LyricRecogniserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LyricRecogniserFragmentBuilder {
    @ContributesAndroidInjector
    abstract fun contributeLyricRecogniserFragment(): LyricRecogniserFragment
}
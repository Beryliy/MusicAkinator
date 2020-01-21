package com.fourcore.musicakinator.di.module.lyricRecogniser

import com.fourcore.musicakinator.di.FragmentScope
import com.fourcore.musicakinator.presentation.lyricRecogniser.LyricRecogniserData
import dagger.Module
import dagger.Provides

@Module
class LyricRecogniserProvidesModule {
    @Provides
    @FragmentScope
    fun provideLyricRecogniserData() = LyricRecogniserData()
}
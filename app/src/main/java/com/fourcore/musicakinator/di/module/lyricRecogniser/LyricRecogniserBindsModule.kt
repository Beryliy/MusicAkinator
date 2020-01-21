package com.fourcore.musicakinator.di.module.lyricRecogniser

import androidx.lifecycle.ViewModel
import com.fourcore.musicakinator.di.factory.ViewModelKey
import com.fourcore.musicakinator.presentation.lyricRecogniser.LyricRecogniserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LyricRecogniserBindsModule {
    @Binds
    @IntoMap
    @ViewModelKey(LyricRecogniserViewModel::class)
    fun bindLyricRecogniserViewModel(lyricRecogniserViewModel: LyricRecogniserViewModel): ViewModel
}
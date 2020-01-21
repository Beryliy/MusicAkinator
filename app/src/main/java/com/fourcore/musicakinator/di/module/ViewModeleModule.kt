package com.fourcore.musicakinator.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.fourcore.musicakinator.di.factory.ViewModelFactory
import com.fourcore.musicakinator.di.factory.ViewModelKey
import com.fourcore.musicakinator.presentation.lyricRecogniser.LyricRecogniserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModeleModule {
    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LyricRecogniserViewModel::class)
    fun bindLyricRecogniserViewModel(lyricRecogniserViewModel: LyricRecogniserViewModel): ViewModel
}
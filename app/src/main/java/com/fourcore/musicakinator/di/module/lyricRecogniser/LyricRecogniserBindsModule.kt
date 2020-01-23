package com.fourcore.musicakinator.di.module.lyricRecogniser

import androidx.lifecycle.ViewModel
import com.fourcore.musicakinator.di.factory.ViewModelKey
import com.fourcore.musicakinator.presentation.game.lyricRecogniser.LyricsRecogniserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LyricRecogniserBindsModule {
    @Binds
    @IntoMap
    @ViewModelKey(LyricsRecogniserViewModel::class)
    fun bindLyricRecogniserViewModel(lyricsRecogniserViewModel: LyricsRecogniserViewModel): ViewModel
}
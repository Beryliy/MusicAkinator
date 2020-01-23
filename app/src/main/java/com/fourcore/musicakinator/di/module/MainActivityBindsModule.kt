package com.fourcore.musicakinator.di.module

import androidx.lifecycle.ViewModel
import com.fourcore.musicakinator.di.factory.ViewModelKey
import com.fourcore.musicakinator.presentation.game.GameViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainActivityBindsModule {
    @Binds
    @IntoMap
    @ViewModelKey(GameViewModel::class)
    fun bindGameViewModel(gameViewModel: GameViewModel): ViewModel
}
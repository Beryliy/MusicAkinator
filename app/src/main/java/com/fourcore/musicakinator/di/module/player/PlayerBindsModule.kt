package com.fourcore.musicakinator.di.module.player

import androidx.lifecycle.ViewModel
import com.fourcore.musicakinator.di.factory.ViewModelKey
import com.fourcore.musicakinator.presentation.game.player.PlayerViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface PlayerBindsModule {
    @Binds
    @IntoMap
    @ViewModelKey(PlayerViewModel::class)
    fun bindPlayerViewModel(playerViewModel: PlayerViewModel): ViewModel
}
package com.fourcore.musicakinator.di.builders

import com.fourcore.musicakinator.di.FragmentScope
import com.fourcore.musicakinator.di.module.lyricRecogniser.LyricRecogniserBindsModule
import com.fourcore.musicakinator.di.module.lyricRecogniser.LyricRecogniserProvidesModule
import com.fourcore.musicakinator.di.module.player.PlayerBindsModule
import com.fourcore.musicakinator.di.module.player.PlayerProvidesModule
import com.fourcore.musicakinator.presentation.game.lyricRecogniser.LyricsRecogniserFragment
import com.fourcore.musicakinator.presentation.game.player.PlayerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {
    @ContributesAndroidInjector(modules = [
        LyricRecogniserBindsModule::class,
        LyricRecogniserProvidesModule::class
    ])
    @FragmentScope
    abstract fun contributeLyricRecogniserFragment(): LyricsRecogniserFragment

    @ContributesAndroidInjector(modules = [
        PlayerBindsModule::class,
        PlayerProvidesModule::class
    ])
    @FragmentScope
    abstract fun contributePlayerFragment(): PlayerFragment
}
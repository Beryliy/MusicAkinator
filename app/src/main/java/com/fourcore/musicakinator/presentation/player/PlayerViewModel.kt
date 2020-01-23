package com.fourcore.musicakinator.presentation.player

import com.fourcore.musicakinator.di.FragmentScope
import com.fourcore.musicakinator.presentation.BaseViewModel
import javax.inject.Inject

@FragmentScope
class PlayerViewModel @Inject constructor(val playerData: PlayerData): BaseViewModel() {
}
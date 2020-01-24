package com.fourcore.musicakinator.presentation.game

import androidx.lifecycle.MutableLiveData
import com.fourcore.musicakinator.R
import com.fourcore.musicakinator.di.ActivityScope
import com.fourcore.musicakinator.domain.GameResult
import com.fourcore.musicakinator.global.proxy.ResourcesRepository
import com.fourcore.musicakinator.presentation.BaseViewModel
import com.fourcore.musicakinator.presentation.game.lyricRecogniser.LyricRecogniserData
import javax.inject.Inject

@ActivityScope
class GameViewModel @Inject constructor(
    resourcesRepository: ResourcesRepository
): BaseViewModel() {
    lateinit var lyricRecogniserData: LyricRecogniserData
    val numRounds = resourcesRepository.getInteger(R.integer.num_rounds)
    val gameLiveResult = MutableLiveData<GameResult>()
    private var roundNumber = 0

    fun confirmTrackGuess() {
        gameLiveResult.value = GameResult.APPS_WIN
    }

    fun declineTrackGuess() {
        if(++roundNumber < numRounds) {
            lyricRecogniserData.gameProgress = roundNumber
        } else {
            gameLiveResult.value = GameResult.USERS_WIN
            roundNumber = 0
            lyricRecogniserData.gameProgress = roundNumber
        }
    }
}
package com.fourcore.musicakinator.presentation.game

import androidx.lifecycle.MutableLiveData
import com.fourcore.musicakinator.di.ActivityScope
import com.fourcore.musicakinator.domain.GameResult
import com.fourcore.musicakinator.presentation.BaseViewModel
import javax.inject.Inject

@ActivityScope
class GameViewModel @Inject constructor(): BaseViewModel() {
    val gameLiveResult = MutableLiveData<GameResult>()
    private var roundNumber = 0
    val roundLiveNumber = MutableLiveData<Int>()

    fun confirmTrackGuess() {
        gameLiveResult.value = GameResult.APPS_WIN
    }

    fun declineTrackGuess() {
        if(++roundNumber > 4) {
            gameLiveResult.value = GameResult.USERS_WIN
            roundNumber = 0
            roundLiveNumber.value = roundNumber

        } else {
            roundLiveNumber.value = ++roundNumber
        }
    }
}
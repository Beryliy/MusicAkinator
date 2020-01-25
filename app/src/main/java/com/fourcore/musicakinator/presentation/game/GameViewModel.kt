package com.fourcore.musicakinator.presentation.game

import androidx.lifecycle.MutableLiveData
import com.deezer.sdk.model.Track
import com.fourcore.musicakinator.R
import com.fourcore.musicakinator.SingleLiveEvent
import com.fourcore.musicakinator.di.ActivityScope
import com.fourcore.musicakinator.domain.GameResult
import com.fourcore.musicakinator.global.proxy.ResourcesRepository
import com.fourcore.musicakinator.network.pojo.Result
import com.fourcore.musicakinator.presentation.BaseViewModel
import com.fourcore.musicakinator.presentation.game.lyricRecogniser.LyricRecogniserData
import javax.inject.Inject

@ActivityScope
class GameViewModel @Inject constructor(
    resourcesRepository: ResourcesRepository
): BaseViewModel() {
    val trackLivaData = MutableLiveData<Track>()
    val tracksHistory = mutableListOf<Result>()
    lateinit var lyricRecogniserData: LyricRecogniserData
    val numRounds = resourcesRepository.getInteger(R.integer.num_rounds)
    val answerEvent = SingleLiveEvent<Unit>()
    val gameOverEvent = SingleLiveEvent<GameResult>()

    fun confirmTrackGuess() {
        answerEvent.call()
        gameOverEvent.value = GameResult.APPS_WIN
        tracksHistory.clear()
    }

    fun declineTrackGuess() {
        answerEvent.call()
        if(lyricRecogniserData.gameProgress < numRounds) {
            lyricRecogniserData.gameProgress++
            tracksHistory += Result(
                    trackLivaData.value!!.title,
                    trackLivaData.value!!.artist.name
                )
        } else {
            gameOverEvent.value = GameResult.USERS_WIN
            lyricRecogniserData.gameProgress = 1
            tracksHistory.clear()
        }
    }
}
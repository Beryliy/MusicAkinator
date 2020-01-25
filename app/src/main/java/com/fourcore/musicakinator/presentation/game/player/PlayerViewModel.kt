package com.fourcore.musicakinator.presentation.game.player

import androidx.lifecycle.MutableLiveData
import com.deezer.sdk.model.Track
import com.deezer.sdk.network.connect.DeezerConnect
import com.deezer.sdk.network.request.DeezerRequestFactory
import com.deezer.sdk.network.request.SearchResultOrder
import com.deezer.sdk.network.request.event.JsonRequestListener
import com.fourcore.musicakinator.R
import com.fourcore.musicakinator.SingleLiveEvent
import com.fourcore.musicakinator.di.FragmentScope
import com.fourcore.musicakinator.domain.GameResult
import com.fourcore.musicakinator.presentation.BaseViewModel
import java.lang.Exception
import javax.inject.Inject

@FragmentScope
class PlayerViewModel @Inject constructor(
    val playerData: PlayerData
): BaseViewModel() {
    var track: Track? = null
    set(value) {
        updatePlayerData(value!!)
        field = value
    }
    val trackLiveData = MutableLiveData<Long>()
    val pauseLiveEvent = SingleLiveEvent<Unit>()
    var changeState: () -> Unit = this::play

    private fun updatePlayerData(track: Track) {
        playerData.trackTitle = track.title
        playerData.artist = track.artist.name
        playerData.albumCoverUrl = track.album.bigImageUrl
    }

    fun playerAct() {
        changeState()
    }

    fun trackEndsPlay() {
        changeState = this::play
        playerData.playerButtonIconResource = R.drawable.ic_action_play
    }

    private fun play() {
        changeState = this::pause
        playerData.playerButtonIconResource = R.drawable.ic_action_pause
        trackLiveData.value = track!!.id
    }

    private fun pause() {
        changeState = this::play
        playerData.playerButtonIconResource = R.drawable.ic_action_play
        pauseLiveEvent.call()
    }
}
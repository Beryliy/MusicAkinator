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
    val deezerConnect: DeezerConnect,
    val playerData: PlayerData
): BaseViewModel() {
    lateinit var track: Track
    val trackLiveData = MutableLiveData<Long>()
    val pauseLiveEvent = SingleLiveEvent<Unit>()
    var changeState: () -> Unit = this::play
    fun findTrack(
        trackName: String,
        artist: String
    ) {
        val query = "$trackName $artist"
        val deezerRequest = DeezerRequestFactory.requestSearchTracks(
            query,
            SearchResultOrder.Ranking
        )
        deezerConnect.requestAsync(
            deezerRequest,
            object: JsonRequestListener() {
                override fun onResult(result: Any?, requestId: Any?) {
                    val tracks = result as List<Track>
                    if(tracks.isEmpty()){

                    } else {
                        track = tracks.first()
                        updatePlayerUI(track)
                    }
                }

                override fun onUnparsedResult(p0: String?, p1: Any?) {
                }

                override fun onException(p0: Exception?, p1: Any?) {
                }

            })
    }

    private fun updatePlayerUI(track: Track) {
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
        trackLiveData.value = track.id
    }

    private fun pause() {
        changeState = this::play
        playerData.playerButtonIconResource = R.drawable.ic_action_play
        pauseLiveEvent.call()
    }
}
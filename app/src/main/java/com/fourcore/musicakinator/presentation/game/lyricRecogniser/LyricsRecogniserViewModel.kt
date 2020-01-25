package com.fourcore.musicakinator.presentation.game.lyricRecogniser

import android.util.Log
import com.deezer.sdk.model.Track
import com.deezer.sdk.network.connect.DeezerConnect
import com.deezer.sdk.network.request.DeezerRequestFactory
import com.deezer.sdk.network.request.SearchResultOrder
import com.deezer.sdk.network.request.event.JsonRequestListener
import com.fourcore.musicakinator.SingleLiveEvent
import com.fourcore.musicakinator.di.FragmentScope
import com.fourcore.musicakinator.network.repository.FindLyricsRepository
import com.fourcore.musicakinator.presentation.BaseViewModel
import com.fourcore.musicakinator.presentation.game.GameViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@FragmentScope
class LyricsRecogniserViewModel @Inject constructor(
    val findLyricsRepository: FindLyricsRepository,
    val deezerConnect: DeezerConnect,
    val lyricRecogniserData: LyricRecogniserData
): BaseViewModel() {
    lateinit var gameViewModel: GameViewModel
    val songRecognisedEvent = SingleLiveEvent<Unit>()
    val showProgressEvent = SingleLiveEvent<Unit>()

    fun bindGameViewModel(gameViewModel: GameViewModel) {
        this.gameViewModel = gameViewModel
    }

    fun recogniseLyrics() {
        showProgressEvent.call()
        launch {
            val auddIOResponse = findLyricsRepository.findSongByLyrics(lyricRecogniserData.lyrics)
            if(auddIOResponse.status == "success") {
                val trackInfo = auddIOResponse.result.first()
                val query = "${trackInfo.title} ${trackInfo.artist}"
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
                                gameViewModel.trackLivaData.postValue(tracks.first())
                                songRecognisedEvent.call()
                            }
                        }

                        override fun onUnparsedResult(p0: String?, p1: Any?) {
                        }

                        override fun onException(p0: Exception?, p1: Any?) {
                        }

                    })
            } else {
                //show error screen
                Log.d(TAG, "error while recognise l")
            }
        }
    }

    companion object {
        const val TAG = "LyricRecogniserVM"
    }
}
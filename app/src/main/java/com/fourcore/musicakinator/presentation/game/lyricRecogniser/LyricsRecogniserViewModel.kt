package com.fourcore.musicakinator.presentation.game.lyricRecogniser

import com.deezer.sdk.model.Track
import com.deezer.sdk.network.connect.DeezerConnect
import com.deezer.sdk.network.request.DeezerRequestFactory
import com.deezer.sdk.network.request.SearchResultOrder
import com.deezer.sdk.network.request.event.JsonRequestListener
import com.fourcore.musicakinator.R
import com.fourcore.musicakinator.SingleLiveEvent
import com.fourcore.musicakinator.di.FragmentScope
import com.fourcore.musicakinator.global.proxy.ResourcesRepository
import com.fourcore.musicakinator.network.repository.FindLyricsRepository
import com.fourcore.musicakinator.presentation.BaseViewModel
import com.fourcore.musicakinator.presentation.error.AkinatorError
import com.fourcore.musicakinator.presentation.game.GameViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

@FragmentScope
class LyricsRecogniserViewModel @Inject constructor(
    val findLyricsRepository: FindLyricsRepository,
    val deezerConnect: DeezerConnect,
    val lyricRecogniserData: LyricRecogniserData,
    val resourcesRepository: ResourcesRepository
): BaseViewModel() {
    lateinit var gameViewModel: GameViewModel
    val songRecognisedEvent = SingleLiveEvent<Unit>()
    val showProgressEvent = SingleLiveEvent<Unit>()
    val errorEvent = SingleLiveEvent<AkinatorError>()

    fun bindGameViewModel(gameViewModel: GameViewModel) {
        this.gameViewModel = gameViewModel
    }

    fun recogniseLyrics() {
        showProgressEvent.call()
        launch {
            try {
                val auddIOResponse = findLyricsRepository.findSongByLyrics(lyricRecogniserData.lyrics)
                if(auddIOResponse.status == "success") {
                    if(auddIOResponse.result.isEmpty()) {
                        gameViewModel.declineTrackGuess()
                        errorEvent.postValue(
                            AkinatorError(resourcesRepository.getString(R.string.lyricsWasntRecognised))
                        )
                    } else {
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

                                override fun onException(exception: Exception?, requestId: Any?) {
                                    when (exception!!::class) {
                                        IOException::class -> errorEvent.postValue(
                                            AkinatorError(resourcesRepository.getString(R.string.noNetwork))
                                        )
                                        else -> errorEvent.postValue(
                                            AkinatorError(resourcesRepository.getString(
                                                R.string.unknownError,
                                                0,
                                                if(exception.message === null) "" else exception.message!!
                                            ))
                                        )
                                    }
                                }
                            })
                    }
                } else {
                    val error = auddIOResponse.error
                    when (error.error_code) {
                        REQUETS_RUN_OUT -> errorEvent.postValue(
                            AkinatorError(resourcesRepository.getString(R.string.auddRequestsRunOut))
                        )
                        else -> errorEvent.postValue(
                            AkinatorError(resourcesRepository.getString(
                                R.string.unknownError,
                                error.error_code,
                                error.error_message
                            ))
                        )
                    }
                }
            } catch (ioe: IOException) {

                errorEvent.postValue(
                    AkinatorError(resourcesRepository.getString(R.string.noNetwork))
                )
            }
        }
    }

    companion object {
        const val TAG = "LyricRecogniserVM"
        const val REQUETS_RUN_OUT = 901
    }
}
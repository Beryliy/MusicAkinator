package com.fourcore.musicakinator.presentation.game.lyricRecogniser

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.fourcore.musicakinator.SingleLiveEvent
import com.fourcore.musicakinator.di.FragmentScope
import com.fourcore.musicakinator.network.pojo.Result
import com.fourcore.musicakinator.network.repository.FindLyricsRepository
import com.fourcore.musicakinator.presentation.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@FragmentScope
class LyricsRecogniserViewModel @Inject constructor(
    val findLyricsRepository: FindLyricsRepository,
    val lyricRecogniserData: LyricRecogniserData
): BaseViewModel() {
    val songRecognisedEvent = SingleLiveEvent<Result>()
    val showProgressEvent = SingleLiveEvent<Unit>()
    fun recogniseLyrics() {
        showProgressEvent.call()
        launch {
            val auddIOResponse = findLyricsRepository.findSongByLyrics(lyricRecogniserData.lyrics)
            if(auddIOResponse.status == "success") {
                songRecognisedEvent.postValue(auddIOResponse.result.first())
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
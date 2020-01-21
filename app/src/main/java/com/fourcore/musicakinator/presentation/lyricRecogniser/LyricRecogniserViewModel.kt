package com.fourcore.musicakinator.presentation.lyricRecogniser

import androidx.lifecycle.MutableLiveData
import com.fourcore.musicakinator.di.FragmentScope
import com.fourcore.musicakinator.network.pojo.Result
import com.fourcore.musicakinator.network.repository.FindLyricsRepository
import com.fourcore.musicakinator.presentation.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@FragmentScope
class LyricRecogniserViewModel @Inject constructor(
    val findLyricsRepository: FindLyricsRepository,
    val lyricRecogniserData: LyricRecogniserData
): BaseViewModel() {
    val songLiveData = MutableLiveData<Result>()
    fun recogniseLyrics() {
        launch {
            val auddIOResponse = findLyricsRepository.findSongByLyrics(lyricRecogniserData.lyrics)
            if(auddIOResponse.status == "success") {
                songLiveData.postValue(auddIOResponse.result.first())
            } else {
                //show error screen
            }
        }
    }
}
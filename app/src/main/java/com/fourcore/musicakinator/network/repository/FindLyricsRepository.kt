package com.fourcore.musicakinator.network.repository

import com.fourcore.musicakinator.network.service.FindLyricsService
import retrofit2.Retrofit
import javax.inject.Inject

class FindLyricsRepository @Inject constructor(
    val findLyricsService: FindLyricsService
) {
    suspend fun findSongByLyrics(lyrics: String) = findLyricsService.findLyrics(lyrics)
}
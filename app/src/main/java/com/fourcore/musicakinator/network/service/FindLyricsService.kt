package com.fourcore.musicakinator.network.service

import AuddIOResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FindLyricsService {
    @GET("/findLyrics")
    suspend fun findLyrics(@Query("q") lyrics: String): AuddIOResponse
}
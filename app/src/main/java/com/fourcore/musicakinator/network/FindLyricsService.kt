package com.fourcore.musicakinator.network

import AuddIOResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FindLyricsService {
    @GET("/findLyrics")
    suspend fun findLyrics(@Query("q") lyrics: String): AuddIOResponse
}
package com.fourcore.musicakinator.network

import retrofit2.http.GET
import retrofit2.http.Query

interface FindLyricsService {
    @GET("/findLyrics")
    fun findLyrics(@Query("q") lyrics: String)
}
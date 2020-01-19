package com.fourcore.musicakinator.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface FindLyricsService {
    @GET("/findLyrics")
    fun findLyrics(@Query("q") lyrics: String): Observable
}
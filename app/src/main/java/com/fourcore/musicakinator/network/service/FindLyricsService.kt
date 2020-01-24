package com.fourcore.musicakinator.network.service

import com.fourcore.musicakinator.network.pojo.AuddIOResponce
import retrofit2.http.GET
import retrofit2.http.Query

interface FindLyricsService {
    @GET("/findLyrics/?api_token=8ee9ddc603359678c585d53e553c8914")
    suspend fun findLyrics(@Query("q") lyrics: String): AuddIOResponce
}
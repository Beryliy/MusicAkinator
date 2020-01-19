package com.fourcore.musicakinator.network.pojo

data class Result(
    val artist: String,
    val artist_id: String,
    val full_title: String,
    val lyrics: String,
    val media: String,
    val song_id: String,
    val title: String,
    val title_with_featured: String
)
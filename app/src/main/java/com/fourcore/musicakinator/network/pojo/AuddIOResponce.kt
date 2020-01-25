package com.fourcore.musicakinator.network.pojo

data class AuddIOResponce(
    val status: String,
    val result: List<Result>,
    val error: Error
)
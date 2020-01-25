package com.fourcore.musicakinator.network.pojo

data class Result(
    val title: String,
    val artist: String
){
    override fun equals(other: Any?): Boolean {
        if(this === other) return true
        if(other?.javaClass != javaClass) return false
        other as Result
        if(this.title != other.title) return false
        if(this.artist != other.artist) return false

        return true
    }

    override fun hashCode(): Int {
        return title.hashCode() + artist.hashCode()
    }
}
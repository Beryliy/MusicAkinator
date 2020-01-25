package com.fourcore.musicakinator

sealed class DataResult<out T : Any> {

    class DataSuccess<out T : Any>(val data: T) : DataResult<T>()

    class DataError(val exception: Throwable) : DataResult<Nothing>()

    override fun toString(): String {
        return when(this) {
            is DataSuccess -> "Result.Success: ${this.data}"
            is DataError -> "Result.AkinatorError: ${this.exception}"
        }
    }
}


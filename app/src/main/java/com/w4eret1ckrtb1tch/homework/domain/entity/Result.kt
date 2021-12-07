package com.w4eret1ckrtb1tch.homework.domain.entity

sealed class Result<out T> {

    data class Success<out T>(val value: T) : Result<T>()
    data class Failure(val exception: Throwable?) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[value=$value]"
            is Failure -> "Failure[exception=$exception]"
            is Loading -> "Loading..."
        }
    }
}


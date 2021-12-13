package com.w4eret1ckrtb1tch.homework.presentation.model

import com.w4eret1ckrtb1tch.homework.presentation.utils.ErrorParserHelper.parseHttpException
import retrofit2.HttpException

sealed class Result<out T> {

    data class Success<out T>(val value: T) : Result<T>()

    data class Failure(val exception: Throwable?) : Result<Nothing>() {
        private val message = when (exception) {
            is HttpException -> parseHttpException(exception)
            else -> exception?.message
        }

        override fun toString(): String {
            return message ?: "Unknown error"
        }
    }

    object Loading : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[value=$value]"
            is Failure -> "Failure[exception=$exception]"
            is Loading -> "Loading..."
        }
    }
}


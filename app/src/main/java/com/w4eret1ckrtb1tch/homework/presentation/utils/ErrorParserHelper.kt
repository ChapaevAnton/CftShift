package com.w4eret1ckrtb1tch.homework.presentation.utils

import retrofit2.HttpException

object ErrorParserHelper {

    fun parseHttpException(exception: HttpException): String? {
        val regex = """\"[^\"]*\"""".toRegex()
        val response = exception.response()
        val match = response?.errorBody()?.string()?.let { regex.find(it) }
        return match?.value
    }
}
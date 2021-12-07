package com.w4eret1ckrtb1tch.homework.data.datasource

import com.w4eret1ckrtb1tch.homework.data.dto.UserResponse
import com.w4eret1ckrtb1tch.homework.domain.entity.UserAuth
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface FocusStartApi {

    @POST("registration")
    fun registerUser(@Body userAuth: UserAuth): Single<UserResponse>

    @POST("login")
    fun loginUser(@Body userAuth: UserAuth): Single<String>

    companion object {
        const val BASE_URL = "http://focusstart.appspot.com/"
        const val ROLE_ADMIN = "ADMIN"
        const val ROLE_USER = "USER"
    }
}
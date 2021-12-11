package com.w4eret1ckrtb1tch.homework.data.datasource.network

import com.w4eret1ckrtb1tch.homework.data.dto.LoansResponse
import com.w4eret1ckrtb1tch.homework.data.dto.UserResponse
import com.w4eret1ckrtb1tch.homework.domain.entity.UserAuth
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface FocusStartApi {

    @POST("registration")
    fun registerUser(@Body userAuth: UserAuth): Single<UserResponse>

    @POST("login")
    fun loginUser(@Body userAuth: UserAuth): Single<String>

    @GET("loans/all")
    fun getLoans(@Header("Authorization") authToken: String): Single<LoansResponse>

    companion object {
        const val BASE_URL = "https://focusstart.appspot.com/"
        const val DATE_FORMAT_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
        const val ROLE_ADMIN = "ADMIN"
        const val ROLE_USER = "USER"
        const val LOAN_STATE_APPROVED = "APPROVED"
        const val LOAN_STATE_REGISTERED = "REGISTERED"
        const val LOAN_STATE_REJECTED = "REJECTED"

    }
}
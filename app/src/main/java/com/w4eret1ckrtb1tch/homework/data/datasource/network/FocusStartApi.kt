package com.w4eret1ckrtb1tch.homework.data.datasource.network

import com.w4eret1ckrtb1tch.homework.data.dto.LoanConditionsResponse
import com.w4eret1ckrtb1tch.homework.data.dto.LoanDataResponse
import com.w4eret1ckrtb1tch.homework.data.dto.LoansListResponse
import com.w4eret1ckrtb1tch.homework.data.dto.UserResponse
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanRequest
import com.w4eret1ckrtb1tch.homework.domain.entity.UserAuth
import io.reactivex.rxjava3.core.Single
import retrofit2.http.*

interface FocusStartApi {

    @POST("registration")
    fun registerUser(@Body userAuth: UserAuth): Single<UserResponse>

    @POST("login")
    fun loginUser(@Body userAuth: UserAuth): Single<String>

    @GET("loans/all")
    fun getLoans(@Header("Authorization") authToken: String): Single<LoansListResponse>

    @GET("loans/{id}")
    fun getLoan(
        @Header("Authorization") authToken: String,
        @Path("id") id: Long
    ): Single<LoanDataResponse>

    @GET("loans/conditions")
    fun getConditions(@Header("Authorization") authToken: String): Single<LoanConditionsResponse>

    @GET("/loans")
    fun createLoan(
        @Header("Authorization") authToken: String,
        @Body loanRequest: LoanRequest
    ): Single<LoanDataResponse>

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
package com.w4eret1ckrtb1tch.homework.data.datasource

import com.w4eret1ckrtb1tch.homework.data.dto.UploadResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ConvertApi {

    @Multipart
    @POST("upload")
    fun uploadImage(
        @Part image: MultipartBody.Part,
        @Part("filename") fileName: RequestBody
    ): Call<UploadResponse>


    companion object {
        const val BASE_URL = "https://v2.convertapi.com/"
    }
}
package com.w4eret1ckrtb1tch.homework.data.repository

import android.net.Uri
import com.w4eret1ckrtb1tch.homework.data.datasource.ContentDataSource
import com.w4eret1ckrtb1tch.homework.data.datasource.ConvertApi
import com.w4eret1ckrtb1tch.homework.data.datasource.UploadRequestBody
import com.w4eret1ckrtb1tch.homework.data.dto.UploadResponse
import com.w4eret1ckrtb1tch.homework.domain.entity.Result
import com.w4eret1ckrtb1tch.homework.domain.entity.ResultCallback
import com.w4eret1ckrtb1tch.homework.domain.repository.UploadRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class UploadRepositoryImpl @Inject constructor(
    private val api: ConvertApi,
    private val contentDataSource: ContentDataSource
) : UploadRepository {

    override fun uploadImage(selectedImageUri: Uri?, resultCallback: ResultCallback) {
        if (selectedImageUri == null) return

        val file = contentDataSource.getFile(selectedImageUri) ?: return
        val body = UploadRequestBody(file, "image")
        api.uploadImage(
            MultipartBody.Part.createFormData(
                "image",
                file.name,
                body
            ),
            RequestBody.create("multipart/form-data".toMediaTypeOrNull(), "json")
        ).enqueue(object : Callback<UploadResponse> {
            override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                resultCallback.result(Result.Failure(t))
            }

            override fun onResponse(
                call: Call<UploadResponse>,
                response: Response<UploadResponse>
            ) {
                response.body()?.let {
                    resultCallback.result(Result.Success(it))
                }
            }
        })
    }
}
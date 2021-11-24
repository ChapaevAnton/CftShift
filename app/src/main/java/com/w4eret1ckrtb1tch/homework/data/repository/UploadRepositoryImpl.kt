package com.w4eret1ckrtb1tch.homework.data.repository

import android.net.Uri
import com.w4eret1ckrtb1tch.homework.data.datasource.local.ContentDataSource
import com.w4eret1ckrtb1tch.homework.data.datasource.remote.ConvertApi
import com.w4eret1ckrtb1tch.homework.data.datasource.remote.UploadRequestBody
import com.w4eret1ckrtb1tch.homework.data.dto.UploadResponse
import com.w4eret1ckrtb1tch.homework.domain.entity.Result
import com.w4eret1ckrtb1tch.homework.domain.repository.UploadRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.FileNotFoundException
import javax.inject.Inject

class UploadRepositoryImpl @Inject constructor(
    private val api: ConvertApi,
    private val contentDataSource: ContentDataSource
) : UploadRepository {

    override fun uploadImage(
        selectedImageUri: Uri?,
        resultCallback: (result: Result<UploadResponse>) -> Unit,
        uploadCallback: (uploadPercentage: Int) -> Unit
    ) {
        if (selectedImageUri == null) {
            resultCallback.invoke(Result.Failure(IllegalArgumentException(FILE_NOT_SELECTED)))
            return
        }
        val file = contentDataSource.getFile(selectedImageUri)
        if (file == null) {
            resultCallback.invoke(Result.Failure(FileNotFoundException(FILE_NOT_FOUND)))
            return
        }
        val body = UploadRequestBody(file, CONTENT_TYPE, uploadCallback)
        api.uploadImage(
            MultipartBody.Part.createFormData(
                CONTENT_TYPE,
                file.name,
                body
            ),
            RESPONSE_TYPE.toRequestBody(MEDIA_TYPE.toMediaTypeOrNull())
        ).enqueue(object : Callback<UploadResponse> {
            override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                resultCallback.invoke(Result.Failure(t))
            }

            override fun onResponse(
                call: Call<UploadResponse>,
                response: Response<UploadResponse>
            ) {
                response.body()?.let {
                    resultCallback.invoke(Result.Success(it))
                }
            }
        })
    }

    private companion object {
        const val CONTENT_TYPE = "image"
        const val MEDIA_TYPE = "multipart/form-data"
        const val RESPONSE_TYPE = "json"
        const val FILE_NOT_FOUND = "File not found"
        const val FILE_NOT_SELECTED = "File not selected"
    }
}
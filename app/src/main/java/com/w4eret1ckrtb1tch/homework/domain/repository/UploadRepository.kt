package com.w4eret1ckrtb1tch.homework.domain.repository

import android.net.Uri
import com.w4eret1ckrtb1tch.homework.data.dto.UploadResponse
import com.w4eret1ckrtb1tch.homework.domain.entity.Result

interface UploadRepository {

    fun uploadImage(
        selectedImageUri: Uri?,
        resultCallback: (result: Result<UploadResponse>) -> Unit,
        uploadCallback: (uploadPercentage: Int) -> Unit
    )

}
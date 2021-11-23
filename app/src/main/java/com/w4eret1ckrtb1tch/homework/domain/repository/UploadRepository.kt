package com.w4eret1ckrtb1tch.homework.domain.repository

import android.net.Uri
import com.w4eret1ckrtb1tch.homework.domain.entity.ResultCallback

interface UploadRepository {

    fun uploadImage(selectedImageUri: Uri?, resultCallback: ResultCallback)

}
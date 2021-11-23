package com.w4eret1ckrtb1tch.homework.domain.entity

import com.w4eret1ckrtb1tch.homework.data.dto.UploadResponse

fun interface ResultCallback {
    fun result(result: Result<UploadResponse>)
}
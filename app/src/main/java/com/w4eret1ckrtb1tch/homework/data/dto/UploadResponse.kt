package com.w4eret1ckrtb1tch.homework.data.dto

import com.google.gson.annotations.SerializedName

data class UploadResponse(
    @field:SerializedName("FileId") val fileId: String?,
    @field:SerializedName("FileName") val fileName: String?,
    @field:SerializedName("FileExt") val fileExt: String?,
    @field:SerializedName("Url") val url: String?
)
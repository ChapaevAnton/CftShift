package com.w4eret1ckrtb1tch.homework.data.datasource

fun interface UploadCallback {

    fun onProgressUpdate(uploadPercentage: Int)
}
package com.w4eret1ckrtb1tch.homework.data.datasource

import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okio.BufferedSink
import java.io.File
import java.io.FileInputStream

class UploadRequestBody(
    private val file: File,
    private val contentType: String,
) : RequestBody() {

    override fun contentLength(): Long = file.length()

    override fun contentType(): MediaType? = "$contentType/*".toMediaTypeOrNull()

    override fun writeTo(sink: BufferedSink) {
        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
        val fileInputStream = FileInputStream(file)
        fileInputStream.use { inputStream ->
            var read: Int
            while (inputStream.read(buffer).also { read = it } != -1) {
                sink.write(buffer, 0, read)
            }
        }
    }

    companion object {
        private const val DEFAULT_BUFFER_SIZE = 2048
    }
}
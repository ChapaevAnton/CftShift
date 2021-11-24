package com.w4eret1ckrtb1tch.homework.data.datasource.remote

import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okio.BufferedSink
import java.io.File
import java.io.FileInputStream

class UploadRequestBody(
    private val file: File,
    private val contentType: String,
    private val uploadCallback: (uploadPercentage: Int) -> Unit
) : RequestBody() {

    override fun contentLength(): Long = file.length()

    override fun contentType(): MediaType? = "$contentType/*".toMediaTypeOrNull()

    override fun writeTo(sink: BufferedSink) {
        val length = file.length()
        var uploaded = UPLOAD_STARTED
        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
        val fileInputStream = FileInputStream(file)
        fileInputStream.use { inputStream ->
            var read: Int
            while (inputStream.read(buffer).also { read = it } != -1) {
                sink.write(buffer, 0, read)
                uploaded += read
                uploadCallback.invoke((UPLOAD_COMPLETE * uploaded / length).toInt())
            }
            uploadCallback.invoke(UPLOAD_COMPLETE.toInt())
        }
    }

    private companion object {
        const val DEFAULT_BUFFER_SIZE = 2048
        const val UPLOAD_STARTED = 0L
        const val UPLOAD_COMPLETE = 100L
    }

}
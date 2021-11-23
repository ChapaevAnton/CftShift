package com.w4eret1ckrtb1tch.homework.data.datasource

import android.os.Handler
import android.os.Looper
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
        var uploaded = INIT_UPLOAD
        val handler = Handler(Looper.getMainLooper())
        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
        val fileInputStream = FileInputStream(file)
        fileInputStream.use { inputStream ->
            var read: Int
            while (inputStream.read(buffer).also { read = it } != -1) {
                handler.post(ProgressUpdater(uploaded, length))
                uploaded += read
                sink.write(buffer, 0, read)
            }
        }
    }

    companion object {
        private const val DEFAULT_BUFFER_SIZE = 2048
        const val INIT_UPLOAD = 0L
    }

    inner class ProgressUpdater(
        private val uploaded: Long,
        private val total: Long
    ) : Runnable {
        override fun run() {
            uploadCallback.invoke((100 * uploaded / total).toInt())
        }
    }
}
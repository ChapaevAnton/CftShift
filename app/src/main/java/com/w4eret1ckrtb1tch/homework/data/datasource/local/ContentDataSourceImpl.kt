package com.w4eret1ckrtb1tch.homework.data.datasource.local

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import javax.inject.Inject

class ContentDataSourceImpl @Inject constructor(
    @ApplicationContext
    private val context: Context
) : ContentDataSource {

    override fun getFile(fileUri: Uri): File? {
        val parcelFileDescriptor =
            context.contentResolver.openFileDescriptor(fileUri, OPEN_MODE, null) ?: return null
        val inputStream = FileInputStream(parcelFileDescriptor.fileDescriptor)
        val file = File(context.cacheDir, getFileName(fileUri))
        val cacheOutputStream = FileOutputStream(file)
        inputStream.copyTo(cacheOutputStream)
        return file
    }

    private fun getFileName(fileUri: Uri): String {
        var name = ""
        val returnCursor = context.contentResolver.query(fileUri, null, null, null, null)
        returnCursor?.use {
            val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
            it.moveToFirst()
            name = it.getString(nameIndex)
        }
        return name
    }

    private companion object {
        const val OPEN_MODE = "r"
    }
}
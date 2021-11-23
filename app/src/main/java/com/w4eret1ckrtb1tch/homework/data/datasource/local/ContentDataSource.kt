package com.w4eret1ckrtb1tch.homework.data.datasource.local

import android.net.Uri
import java.io.File

interface ContentDataSource {

    fun getFile(fileUri: Uri): File?

}
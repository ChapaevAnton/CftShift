package com.w4eret1ckrtb1tch.homework.presentation

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.w4eret1ckrtb1tch.homework.data.dto.UploadResponse
import com.w4eret1ckrtb1tch.homework.domain.entity.Result
import com.w4eret1ckrtb1tch.homework.domain.repository.UploadRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageLoadViewModel @Inject constructor(
    private val repository: UploadRepository
) : ViewModel() {

    private val uploadResponse: MutableLiveData<Result<UploadResponse>> = MutableLiveData()
    val getUploadResponse: LiveData<Result<UploadResponse>> = uploadResponse

    fun uploadImage(selectedImageUri: Uri?) {
        Log.d("TAG", "uploadImage: ok")
        repository.uploadImage(selectedImageUri) { result -> uploadResponse.value = result }
    }

}
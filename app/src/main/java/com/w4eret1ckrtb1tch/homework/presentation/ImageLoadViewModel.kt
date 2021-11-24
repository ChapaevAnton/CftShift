package com.w4eret1ckrtb1tch.homework.presentation

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.w4eret1ckrtb1tch.homework.data.dto.UploadResponse
import com.w4eret1ckrtb1tch.homework.domain.entity.Result
import com.w4eret1ckrtb1tch.homework.domain.repository.UploadRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageLoadViewModel @Inject constructor(
    private val repository: UploadRepository
) : ViewModel() {

    private val uploadResponse: MutableLiveData<Result<UploadResponse>> = MutableLiveData()
    val getUploadResponse: LiveData<Result<UploadResponse>> = uploadResponse

    private val uploadProgress: MutableLiveData<Int> = MutableLiveData(0)
    val getUploadProgress: LiveData<Int> = uploadProgress

    private val imageUri: MutableLiveData<Uri> = MutableLiveData()
    val getImageUri: LiveData<Uri> = imageUri
    fun setImageUri(value: Uri?) {
        imageUri.value = value
    }

    fun uploadImage() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.uploadImage(imageUri.value, { result ->
                Log.d("TAG", "uploadImage: $result")
                uploadResponse.value = result
            }, { uploadPercentage ->
                CoroutineScope(Dispatchers.Main).launch {
                    uploadProgress.value = uploadPercentage
                }
            })
        }
    }
}
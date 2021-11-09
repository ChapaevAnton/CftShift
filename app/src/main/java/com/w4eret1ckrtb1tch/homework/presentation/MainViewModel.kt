package com.w4eret1ckrtb1tch.homework.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.w4eret1ckrtb1tch.homework.domain.usecase.GetSampleStringFromLocalUseCase
import com.w4eret1ckrtb1tch.homework.domain.usecase.GetSampleStringFromRemoteUseCase

class MainViewModel {
    //TODO: DI
    private val getSampleStringFromLocalUseCase = GetSampleStringFromLocalUseCase()
    private val getSampleStringFromRemoteUseCase = GetSampleStringFromRemoteUseCase()

    private val _state: MutableLiveData<MainState> = MutableLiveData<MainState>()
    val state: LiveData<MainState> = _state

    fun loadStrings() {
        _state.value = MainState.Loading

        val fromLocal = getSampleStringFromLocalUseCase()
        val fromRemote = getSampleStringFromRemoteUseCase()

        _state.value = MainState.Success(remoteString = fromRemote, localString = fromLocal)
    }
}
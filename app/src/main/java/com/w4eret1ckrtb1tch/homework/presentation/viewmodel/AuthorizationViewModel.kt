package com.w4eret1ckrtb1tch.homework.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.w4eret1ckrtb1tch.homework.presentation.utils.Result
import com.w4eret1ckrtb1tch.homework.domain.entity.UserAuth
import com.w4eret1ckrtb1tch.homework.domain.usecase.PostLoginUserUseCase
import com.w4eret1ckrtb1tch.homework.domain.usecase.WriteAuthTokenUseCase
import com.w4eret1ckrtb1tch.homework.presentation.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val userLoginCase: PostLoginUserUseCase,
    private val writeAuthTokenUseCase: WriteAuthTokenUseCase
) : ViewModel() {
    private var userLoginDisposable: Disposable? = null


    private val authToken: SingleLiveEvent<Result<Unit>> = SingleLiveEvent()
    val getAuthToken: LiveData<Result<Unit>> = authToken

    fun signInUser(name: String, password: String) {
        authToken.value = Result.Loading
        userLoginDisposable = userLoginCase(UserAuth(name = name, password = password))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { result ->
                    writeAuthTokenUseCase(result)
                    authToken.value = Result.Success(Unit)
                },
                onError = { error ->
                    authToken.value = Result.Failure(error)
                })
    }

    override fun onCleared() {
        userLoginDisposable?.dispose()
        authToken.call()
        super.onCleared()
    }
}
package com.w4eret1ckrtb1tch.homework.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.w4eret1ckrtb1tch.homework.domain.entity.UserAuth
import com.w4eret1ckrtb1tch.homework.domain.usecase.PostLoginUserUseCase
import com.w4eret1ckrtb1tch.homework.domain.usecase.WriteAuthTokenUseCase
import com.w4eret1ckrtb1tch.homework.presentation.utils.Result
import com.w4eret1ckrtb1tch.homework.presentation.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val userLoginCase: PostLoginUserUseCase,
    private val writeAuthTokenUseCase: WriteAuthTokenUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val authToken: SingleLiveEvent<Result<Unit>> = SingleLiveEvent()
    val getAuthToken: LiveData<Result<Unit>> = authToken

    fun signInUser(name: String, password: String) {
        authToken.value = Result.Loading
        compositeDisposable.add(
            userLoginCase(UserAuth(name = name, password = password))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = { result ->
                        writeAuthToken(result)
                        authToken.value = Result.Success(Unit)
                    },
                    onError = { error ->
                        authToken.value = Result.Failure(error)
                    })
        )
    }

    private fun writeAuthToken(authToken: String) {
        compositeDisposable.add(writeAuthTokenUseCase(authToken)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    Log.d("TAG", "writeAuthToken: OK!")
                },
                onError = { error ->
                    Log.d("TAG", "writeAuthToken: ${error.message}")
                }
            ))
    }

    override fun onCleared() {
        compositeDisposable.clear()
        authToken.call()
        super.onCleared()
    }
}
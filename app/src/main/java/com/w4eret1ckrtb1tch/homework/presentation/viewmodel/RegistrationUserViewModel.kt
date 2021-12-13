package com.w4eret1ckrtb1tch.homework.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.w4eret1ckrtb1tch.homework.domain.entity.UserAuth
import com.w4eret1ckrtb1tch.homework.domain.entity.UserEntity
import com.w4eret1ckrtb1tch.homework.domain.usecase.PostRegisterUserUseCase
import com.w4eret1ckrtb1tch.homework.presentation.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class RegistrationUserViewModel @Inject constructor(
    private val registerUserUseCase: PostRegisterUserUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val user: MutableLiveData<Result<UserEntity>> = MutableLiveData()
    val getUser: LiveData<Result<UserEntity>> = user

    fun registerUser(name: String, password: String) {
        user.value = Result.Loading
        compositeDisposable.add(registerUserUseCase(UserAuth(name = name, password = password))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { result ->
                    user.value = Result.Success(result)
                },
                onError = { error ->
                    // FIXME: 09.12.2021 Определять разные коды ошибок...
                    user.value = Result.Failure(error)
                    Log.d("TAG", "registerUser: ${error.message}")
                }
            ))
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}
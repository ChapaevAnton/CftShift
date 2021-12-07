package com.w4eret1ckrtb1tch.homework.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.w4eret1ckrtb1tch.homework.domain.entity.Result
import com.w4eret1ckrtb1tch.homework.domain.entity.UserAuth
import com.w4eret1ckrtb1tch.homework.domain.usecase.PostLoginUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val userLoginCase: PostLoginUserCase
) : ViewModel() {
    private var userLogin: Disposable? = null


    private val hash: MutableLiveData<Result<String>> = MutableLiveData()
    val getHash: LiveData<Result<String>> = hash

    fun signInUser(name: String, password: String) {
        hash.value = Result.Loading
        userLogin = userLoginCase(UserAuth(name = name, password = password))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                hash.value = Result.Success(result)
            }, { error ->
                hash.value = Result.Failure(error)
            })
    }

    override fun onCleared() {
        super.onCleared()
        userLogin?.dispose()
    }
}
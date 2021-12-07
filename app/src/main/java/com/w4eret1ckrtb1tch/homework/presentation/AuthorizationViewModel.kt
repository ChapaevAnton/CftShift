package com.w4eret1ckrtb1tch.homework.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.w4eret1ckrtb1tch.homework.domain.entity.UserAuth
import com.w4eret1ckrtb1tch.homework.domain.usecase.PostLoginUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class AuthorizationViewModel @Inject constructor(
    private val userLoginCase: PostLoginUserCase
) : ViewModel() {

    private val hash: MutableLiveData<String> = MutableLiveData()
    val getHash: LiveData<String> = hash

    fun loginUser() {
        val user = UserAuth(name = "user", password = "123321")
        userLoginCase(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ hashResult ->
                hash.value = hashResult
            }, { error ->
                Log.d("TAG", "loginUser: ${error.localizedMessage}")
            })
    }

}
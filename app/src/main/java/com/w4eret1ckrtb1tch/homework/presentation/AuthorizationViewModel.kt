package com.w4eret1ckrtb1tch.homework.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.w4eret1ckrtb1tch.homework.domain.entity.Result
import com.w4eret1ckrtb1tch.homework.domain.entity.UserAuth
import com.w4eret1ckrtb1tch.homework.domain.usecase.PostLoginUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import retrofit2.HttpException
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
                // FIXME: 08.12.2021 Определять разные коды ошибок...
                //  https://medium.com/@janczar/http-errors-with-kotlin-rx-and-retrofit-34e905aa91dd
                if (error is HttpException) {
                    val response = error.response()
                    val body = response?.message()
                    val code = response?.code()
                    val raw = response?.errorBody()?.string()

                    Log.d("TAG", "signInUser: $code $body $raw")
                }
                hash.value = Result.Failure(error)
            })
    }

    override fun onCleared() {
        super.onCleared()
        userLogin?.dispose()
    }
}
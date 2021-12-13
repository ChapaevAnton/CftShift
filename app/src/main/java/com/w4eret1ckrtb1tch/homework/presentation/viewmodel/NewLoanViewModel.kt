package com.w4eret1ckrtb1tch.homework.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanConditions
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanEntity
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanRequest
import com.w4eret1ckrtb1tch.homework.domain.usecase.GetConditionsUseCase
import com.w4eret1ckrtb1tch.homework.domain.usecase.PostCreateLoanUseCase
import com.w4eret1ckrtb1tch.homework.domain.usecase.ReadAuthTokenUseCase
import com.w4eret1ckrtb1tch.homework.presentation.model.Result
import com.w4eret1ckrtb1tch.homework.presentation.model.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import java.math.BigDecimal
import javax.inject.Inject

@HiltViewModel
class NewLoanViewModel @Inject constructor(
    private val getConditionsUseCase: GetConditionsUseCase,
    private val postCreateLoanUseCase: PostCreateLoanUseCase,
    private val readAuthTokenUseCase: ReadAuthTokenUseCase
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val conditions: MutableLiveData<Result<LoanConditions>> = MutableLiveData()
    val getConditions: LiveData<Result<LoanConditions>> = conditions
    private val loanInfo: SingleLiveEvent<Result<LoanEntity>> = SingleLiveEvent()
    val getLoanInfo: LiveData<Result<LoanEntity>> = loanInfo

    init {
        getLoanConditions()
    }

    fun createLoan(firstName: String, lastName: String, phoneNumber: String, amount: Long) {
        when (val conditions = conditions.value) {
            is Result.Success -> {
                postCreateLoanRequest(
                    LoanRequest(
                        firstName = firstName,
                        lastName = lastName,
                        phoneNumber = phoneNumber,
                        percent = conditions.value.percent,
                        period = conditions.value.period,
                        amount = BigDecimal(amount)
                    )
                )
            }
            is Result.Failure -> {
                Log.d("TAG", "createLoan: ${conditions.exception}")
            }
            else -> throw ClassCastException("Result missing")
        }
    }

    private fun postCreateLoanRequest(loanRequest: LoanRequest) {
        Log.d("TAG", "postCreateLoanRequest: $loanRequest ")
        loanInfo.value = Result.Loading
        compositeDisposable.add(
            readAuthTokenUseCase()
                .firstOrError()
                .flatMap { authToken -> postCreateLoanUseCase(authToken, loanRequest) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = { result ->
                        loanInfo.value = Result.Success(result)
                    },
                    onError = { error ->
                        loanInfo.value = Result.Failure(error)
                        Log.d("TAG", "postCreateLoanRequest: ${error.message}")
                    }
                )
        )
    }

    private fun getLoanConditions() {
        conditions.value = Result.Loading
        compositeDisposable.add(
            readAuthTokenUseCase()
                .firstOrError()
                .flatMap { authToken -> getConditionsUseCase(authToken) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onSuccess = { result ->
                        conditions.value = Result.Success(result)
                    },
                    onError = { error ->
                        conditions.value = Result.Failure(error)
                        Log.d("TAG", "getLoanConditions: ${error.message}")
                    }
                )
        )
    }

    override fun onCleared() {
        loanInfo.call()
        compositeDisposable.clear()
        super.onCleared()
    }
}
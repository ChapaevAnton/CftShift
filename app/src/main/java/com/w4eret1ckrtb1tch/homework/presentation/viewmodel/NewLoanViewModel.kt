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
import com.w4eret1ckrtb1tch.homework.presentation.model.InputFieldError
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
    private val incorrectInputField: SingleLiveEvent<InputFieldError> = SingleLiveEvent()
    val getIncorrectInputField: LiveData<InputFieldError> = incorrectInputField

    init {
        getLoanConditions()
    }

    fun createLoan(firstName: String, lastName: String, phoneNumber: String, amount: String) {
        if (!validateInputField(firstName, lastName, phoneNumber, amount)) return
        if (!validateMaxAmount(amount.toDouble())) return
        when (val condition = conditions.value) {
            is Result.Success -> {
                postCreateLoanRequest(
                    LoanRequest(
                        firstName = firstName,
                        lastName = lastName,
                        phoneNumber = phoneNumber,
                        percent = condition.value.percent,
                        period = condition.value.period,
                        amount = BigDecimal.valueOf(amount.toDouble())
                    )
                )
            }
            is Result.Failure -> {
                conditions.value = Result.Failure(condition.exception)
            }
            else -> throw ClassCastException("Result missing")
        }
    }

    private fun postCreateLoanRequest(loanRequest: LoanRequest) {
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

    private fun validateInputField(
        firstName: String,
        lastName: String,
        phoneNumber: String,
        amount: String
    ): Boolean {
        if (firstName.isBlank()) {
            incorrectInputField.value = InputFieldError.FIRST_NAME_EMPTY
            return false
        }
        if (lastName.isBlank()) {
            incorrectInputField.value = InputFieldError.LAST_NAME_EMPTY
            return false
        }
        if (phoneNumber.isEmpty()) {
            incorrectInputField.value = InputFieldError.PHONE_NUMBER_EMPTY
            return false
        }
        if (amount.isEmpty()) {
            incorrectInputField.value = InputFieldError.AMOUNT_EMPTY
            return false
        }
        return true
    }

    private fun validateMaxAmount(amount: Double): Boolean {
        val maxAmount = conditions.value?.let { (it as Result.Success).value.maxAmount.toDouble() }
        maxAmount?.let {
            if (amount > it) {
                incorrectInputField.value = InputFieldError.AMOUNT_MAX_VALUE
                return@validateMaxAmount false
            }
            if (amount == 0.0) {
                incorrectInputField.value = InputFieldError.AMOUNT_ZERO
                return@validateMaxAmount false
            }
        }
        return true
    }

    override fun onCleared() {
        loanInfo.call()
        compositeDisposable.clear()
        super.onCleared()
    }
}
package com.w4eret1ckrtb1tch.homework.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanEntity
import com.w4eret1ckrtb1tch.homework.domain.entity.Result
import com.w4eret1ckrtb1tch.homework.domain.usecase.GetLoansListUseCase
import com.w4eret1ckrtb1tch.homework.domain.usecase.ReadAuthTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class LoansListViewModel @Inject constructor(
    private val loansListUseCase: GetLoansListUseCase,
    private val readAuthTokenUseCase: ReadAuthTokenUseCase
) : ViewModel() {

    private var loansListDisposable: Disposable? = null

    private val loans: MutableLiveData<Result<List<LoanEntity>>> = MutableLiveData()
    val getLoans: LiveData<Result<List<LoanEntity>>> = loans

    init {
        getLoansList()
    }

    private fun getLoansList() {
        loans.value = Result.Loading
        loansListDisposable = readAuthTokenUseCase()
            .firstOrError()
            .flatMap { authToken -> loansListUseCase(authToken) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { loansList ->
                    loans.value = Result.Success(loansList)
                },
                onError = { error ->
                    // FIXME: 09.12.2021 Определять разные коды ошибок...
                    loans.value = Result.Failure(error)
                    Log.d("TAG", "getLoansList: ${error.message}")
                })
    }

    override fun onCleared() {
        loansListDisposable?.dispose()
        super.onCleared()
    }
}
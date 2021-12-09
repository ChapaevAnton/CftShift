package com.w4eret1ckrtb1tch.homework.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanEntity
import com.w4eret1ckrtb1tch.homework.domain.usecase.GetLoansListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class LoansListViewModel @Inject constructor(
    private val getLoansListUseCase: GetLoansListUseCase
) : ViewModel() {

    private var loanList: Disposable? = null

    private val loans: MutableLiveData<List<LoanEntity>> = MutableLiveData()
    val getLoans: LiveData<List<LoanEntity>> = loans

    fun getLoansList(authToken: String) {
        loanList = getLoansListUseCase(authToken)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { result ->
                    loans.value = result
                },
                onError = { error ->
                    Log.d("TAG", "getLoansList: ${error.message}")
                })
    }

    override fun onCleared() {
        loanList?.dispose()
        super.onCleared()
    }
}
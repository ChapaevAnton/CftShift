package com.w4eret1ckrtb1tch.homework.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanEntity
import com.w4eret1ckrtb1tch.homework.domain.entity.Result
import com.w4eret1ckrtb1tch.homework.domain.usecase.GetLoanDataUseCase
import com.w4eret1ckrtb1tch.homework.domain.usecase.ReadAuthTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class LoanDataViewModel @Inject constructor(
    private val getLoanDataUseCase: GetLoanDataUseCase,
    private val readAuthTokenUseCase: ReadAuthTokenUseCase
) : ViewModel() {

    private var loanDataDisposable: Disposable? = null

    private val loan: MutableLiveData<Result<LoanEntity>> = MutableLiveData()
    val getLoan: LiveData<Result<LoanEntity>> = loan

    fun getLoanData(idLoan: Long) {
        loanDataDisposable = readAuthTokenUseCase()
            .firstOrError()
            .flatMap { authToken -> getLoanDataUseCase(authToken, idLoan) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { loanData ->
                    loan.value = Result.Success(loanData)
                },
                onError = { error ->
                    // FIXME: 09.12.2021 Определять разные коды ошибок...
                    loan.value = Result.Failure(error)
                    Log.d("TAG", "getLoansList: ${error.message}")
                }
            )
    }

    override fun onCleared() {
        loanDataDisposable?.dispose()
        super.onCleared()
    }

}
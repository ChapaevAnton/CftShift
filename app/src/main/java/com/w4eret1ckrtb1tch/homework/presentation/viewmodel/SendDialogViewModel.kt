package com.w4eret1ckrtb1tch.homework.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SendDialogViewModel @Inject constructor() : ViewModel() {

    private val loan: MutableLiveData<LoanEntity> = MutableLiveData()
    val getLoan: LiveData<LoanEntity> = loan

    fun setLoan(loanEntity: LoanEntity) {
        loan.value = loanEntity
    }
}
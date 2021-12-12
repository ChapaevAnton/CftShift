package com.w4eret1ckrtb1tch.homework.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.w4eret1ckrtb1tch.homework.domain.usecase.GetConditionsUseCase
import com.w4eret1ckrtb1tch.homework.domain.usecase.PostCreateLoanUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewLoanViewModel @Inject constructor(
    private val getConditionsUseCase: GetConditionsUseCase,
    private val postCreateLoanUseCase: PostCreateLoanUseCase
) : ViewModel() {
}
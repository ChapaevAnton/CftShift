package com.w4eret1ckrtb1tch.homework.domain.usecase

import com.w4eret1ckrtb1tch.homework.domain.entity.LoanEntity
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanRequest
import com.w4eret1ckrtb1tch.homework.domain.repository.LoanRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class PostCreateLoanUseCase @Inject constructor(
    private val repository: LoanRepository
) {

    operator fun invoke(authToken: String, loanRequest: LoanRequest): Single<LoanEntity> =
        repository.createLoan(authToken, loanRequest)
}
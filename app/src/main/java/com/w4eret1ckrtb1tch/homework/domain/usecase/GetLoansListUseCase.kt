package com.w4eret1ckrtb1tch.homework.domain.usecase

import com.w4eret1ckrtb1tch.homework.domain.entity.LoanEntity
import com.w4eret1ckrtb1tch.homework.domain.repository.LoanRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetLoansListUseCase @Inject constructor(
    private val repository: LoanRepository
) {
    operator fun invoke(authToken: String): Single<List<LoanEntity>> {
        return repository.getLoans(authToken)
    }
}
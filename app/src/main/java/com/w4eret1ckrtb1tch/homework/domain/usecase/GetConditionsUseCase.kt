package com.w4eret1ckrtb1tch.homework.domain.usecase

import com.w4eret1ckrtb1tch.homework.domain.entity.LoanConditions
import com.w4eret1ckrtb1tch.homework.domain.repository.LoanRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetConditionsUseCase @Inject constructor(
    private val repository: LoanRepository
) {

    operator fun invoke(authToken: String): Single<LoanConditions> =
        repository.getConditions(authToken)
}
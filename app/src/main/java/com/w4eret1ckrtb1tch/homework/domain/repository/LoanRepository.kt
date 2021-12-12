package com.w4eret1ckrtb1tch.homework.domain.repository

import com.w4eret1ckrtb1tch.homework.domain.entity.LoanConditions
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanEntity
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanRequest
import io.reactivex.rxjava3.core.Single

interface LoanRepository {

    fun getLoans(authToken: String): Single<List<LoanEntity>>

    fun getLoan(authToken: String, idLoan: Long): Single<LoanEntity>

    fun createLoan(authToken: String, loanRequest: LoanRequest): Single<LoanEntity>

    fun getConditions(authToken: String): Single<LoanConditions>

}
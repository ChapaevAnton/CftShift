package com.w4eret1ckrtb1tch.homework.domain.repository

import com.w4eret1ckrtb1tch.homework.domain.entity.LoanEntity
import io.reactivex.rxjava3.core.Single

interface LoanRepository {

    fun getLoans(authToken: String): Single<List<LoanEntity>>

    fun getLoan(authToken: String, id: Long): Single<LoanEntity>

}
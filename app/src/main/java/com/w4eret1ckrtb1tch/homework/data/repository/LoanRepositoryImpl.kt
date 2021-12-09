package com.w4eret1ckrtb1tch.homework.data.repository

import com.w4eret1ckrtb1tch.homework.data.datasource.FocusStartApi
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanEntity
import com.w4eret1ckrtb1tch.homework.domain.repository.LoanRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LoanRepositoryImpl @Inject constructor(
    private val api: FocusStartApi
) : LoanRepository {
    override fun getLoans(authToken: String): Single<List<LoanEntity>> {
        TODO("Not yet implemented")
    }
}
package com.w4eret1ckrtb1tch.homework.data.repository

import com.w4eret1ckrtb1tch.homework.data.datasource.network.FocusStartApi
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanConditions
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanEntity
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanRequest
import com.w4eret1ckrtb1tch.homework.domain.mapper.LoanResponseMapper
import com.w4eret1ckrtb1tch.homework.domain.repository.LoanRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class LoanRepositoryImpl @Inject constructor(
    private val api: FocusStartApi,
    private val mapper: LoanResponseMapper
) : LoanRepository {

    override fun getLoans(authToken: String): Single<List<LoanEntity>> {
        return api.getLoans(authToken)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map { mapper.mapResponse(it) }
    }

    override fun getLoan(authToken: String, idLoan: Long): Single<LoanEntity> {
        return api.getLoan(authToken, idLoan)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map { mapper.mapResponse(it) }
    }

    override fun createLoan(authToken: String, loanRequest: LoanRequest): Single<LoanEntity> {
        return api.createLoan(authToken, loanRequest)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map { mapper.mapResponse(it) }
    }

    override fun getConditions(authToken: String): Single<LoanConditions> {
        return api.getConditions(authToken)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map { mapper.mapResponse(it) }
    }
}
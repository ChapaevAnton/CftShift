package com.w4eret1ckrtb1tch.homework.data.repository

import com.w4eret1ckrtb1tch.homework.data.dto.CurrenciesResponse
import com.w4eret1ckrtb1tch.homework.data.dto.CurrencyResponse
import com.w4eret1ckrtb1tch.homework.data.source.DailyApi
import com.w4eret1ckrtb1tch.homework.domain.mapper.CurrenciesMapper
import com.w4eret1ckrtb1tch.homework.domain.model.Currencies
import com.w4eret1ckrtb1tch.homework.domain.repository.CurrenciesRepository
import io.reactivex.Single
import javax.inject.Inject

class CurrenciesRepositoryImpl
@Inject constructor(
    private val api: DailyApi,
    private val mapper: @JvmSuppressWildcards CurrenciesMapper<CurrenciesResponse, CurrencyResponse>
) : CurrenciesRepository {

    override fun getCurrencies(): Single<Currencies> {
        return api.getCurrencies().map { mapper.map(it) }
    }
}
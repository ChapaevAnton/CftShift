package com.w4eret1ckrtb1tch.homework.data.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.w4eret1ckrtb1tch.homework.data.dto.CurrenciesResponse
import com.w4eret1ckrtb1tch.homework.data.dto.CurrencyResponse
import com.w4eret1ckrtb1tch.homework.domain.mapper.CurrenciesMapper
import com.w4eret1ckrtb1tch.homework.domain.model.Currencies
import com.w4eret1ckrtb1tch.homework.domain.repository.CurrenciesRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Single
import java.io.File
import javax.inject.Inject

class MockCurrenciesRepositoryImpl @Inject constructor(
    private val mapper: @JvmSuppressWildcards CurrenciesMapper<CurrenciesResponse, CurrencyResponse>,
    private val gson: Gson,
    @ApplicationContext
    private val context: Context
) : CurrenciesRepository {
    override fun getCurrencies(): Single<Currencies> {
        return getLocalTransactions()
    }

    private fun getLocalTransactions(): Single<Currencies> {
        return Single.create { emitter -> emitter.onSuccess(getCurrenciesMock()) }
    }

    private fun getCurrenciesMock(): Currencies {
        val file = File("data.json")
        val reader = context.assets.open(file.name).bufferedReader().use { it.readText() }
        val currenciesResponse = gson.fromJson(reader, CurrenciesResponse::class.java)
        Log.d("TAG", "getCurrenciesMock: $currenciesResponse")
        return mapper.map(currenciesResponse)
    }

}


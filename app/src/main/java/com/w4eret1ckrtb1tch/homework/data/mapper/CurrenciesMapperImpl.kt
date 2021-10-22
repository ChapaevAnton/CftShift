package com.w4eret1ckrtb1tch.homework.data.mapper

import com.w4eret1ckrtb1tch.homework.data.dto.CurrenciesResponse
import com.w4eret1ckrtb1tch.homework.data.dto.CurrencyResponse
import com.w4eret1ckrtb1tch.homework.domain.mapper.CurrenciesMapper
import com.w4eret1ckrtb1tch.homework.domain.model.Currencies
import com.w4eret1ckrtb1tch.homework.domain.model.Currency
import javax.inject.Inject

class CurrenciesMapperImpl
@Inject constructor() :
    CurrenciesMapper<@JvmSuppressWildcards CurrenciesResponse, @JvmSuppressWildcards CurrencyResponse> {

    override fun map(currencies: CurrenciesResponse): Currencies {
        return with(currencies) {
            Currencies(
                date = date,
                previousDate = previousDate,
                previousURL = previousURL,
                timeStamp = timeStamp,
                currency = currency.mapValues { map(it.value) }.toMap(HashMap())
            )
        }
    }

    override fun map(currency: CurrencyResponse): Currency {
        return with(currency) {
            Currency(
                charCode = charCode,
                id = id,
                name = name,
                nominal = nominal,
                numCode = numCode,
                previous = previous,
                value = value
            )
        }
    }
}
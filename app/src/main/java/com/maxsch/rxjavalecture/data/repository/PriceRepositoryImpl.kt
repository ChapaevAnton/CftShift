package com.maxsch.rxjavalecture.data.repository

import com.maxsch.rxjavalecture.data.datasource.PriceApi
import com.maxsch.rxjavalecture.domain.entities.Animal
import com.maxsch.rxjavalecture.domain.repository.PriceRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class PriceRepositoryImpl(private val priceApi: PriceApi) : PriceRepository {
    override suspend fun getPrice(animal: Animal): Int {
        return coroutineScope {
            val priceList = async { priceApi.getPrice(animal) }
            priceList.await()
        }
    }
}
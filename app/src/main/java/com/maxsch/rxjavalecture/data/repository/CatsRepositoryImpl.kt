package com.maxsch.rxjavalecture.data.repository

import com.maxsch.rxjavalecture.data.datasource.CatsApi
import com.maxsch.rxjavalecture.domain.entities.Cat
import com.maxsch.rxjavalecture.domain.repository.CatsRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class CatsRepositoryImpl @Inject constructor(private val catsApi: CatsApi) : CatsRepository {

    override suspend fun getCats(): List<Cat> {
        return coroutineScope {
            val catsList = async { catsApi.getCats() }
            catsList.await()
        }
    }
}
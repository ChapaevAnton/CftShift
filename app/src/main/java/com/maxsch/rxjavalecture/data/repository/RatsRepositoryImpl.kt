package com.maxsch.rxjavalecture.data.repository

import com.maxsch.rxjavalecture.data.datasource.RatsApi
import com.maxsch.rxjavalecture.domain.entities.Rat
import com.maxsch.rxjavalecture.domain.repository.RatsRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class RatsRepositoryImpl(private val ratsApi: RatsApi) : RatsRepository {

    override suspend fun getRats(): List<Rat> {
        return coroutineScope {
            val ratList = async { ratsApi.getRats() }
            ratList.await()
        }
    }
}
package com.maxsch.rxjavalecture.data.repository

import com.maxsch.rxjavalecture.data.datasource.DogsApi
import com.maxsch.rxjavalecture.domain.entities.Dog
import com.maxsch.rxjavalecture.domain.repository.DogsRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class DogsRepositoryImpl @Inject constructor(private val dogsApi: DogsApi) : DogsRepository {

    override suspend fun getDogs(): List<Dog> {
        return coroutineScope {
            val dogList = async { dogsApi.getDogs() }
            dogList.await()
        }
    }
}
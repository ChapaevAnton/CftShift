package com.w4eret1ckrtb1tch.homework.data.repository

import com.w4eret1ckrtb1tch.homework.data.datasource.SampleStringDataSource
import com.w4eret1ckrtb1tch.homework.di.LocalDataSource
import com.w4eret1ckrtb1tch.homework.di.RemoteDataSource
import com.w4eret1ckrtb1tch.homework.domain.repository.SampleStringRepository
import javax.inject.Inject

class SampleStringRepositoryImpl @Inject constructor(
    @LocalDataSource
    private val localDataSource: SampleStringDataSource,
    @RemoteDataSource
    private val remoteDataSource: SampleStringDataSource
) : SampleStringRepository {

    //TODO: DI OK

    override fun getFromRemote(): String =
        remoteDataSource.get()

    override fun getFromLocal(): String =
        localDataSource.get()
}
package com.w4eret1ckrtb1tch.homework.data.repository

import com.w4eret1ckrtb1tch.homework.data.datasource.SampleStringDataSource
import com.w4eret1ckrtb1tch.homework.data.datasource.SampleStringLocalDataSource
import com.w4eret1ckrtb1tch.homework.data.datasource.SampleStringRemoteDataSource
import com.w4eret1ckrtb1tch.homework.domain.repository.SampleStringRepository

class SampleStringRepositoryImpl : SampleStringRepository {

    //TODO: DI
    private val localDataSource: SampleStringDataSource = SampleStringLocalDataSource()
    private val remoteDataSource: SampleStringDataSource = SampleStringRemoteDataSource()

    override fun getFromRemote(): String =
        remoteDataSource.get()

    override fun getFromLocal(): String =
        localDataSource.get()
}
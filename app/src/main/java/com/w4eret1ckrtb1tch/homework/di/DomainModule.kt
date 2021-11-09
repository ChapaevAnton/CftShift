package com.w4eret1ckrtb1tch.homework.di

import com.w4eret1ckrtb1tch.homework.data.datasource.SampleStringDataSource
import com.w4eret1ckrtb1tch.homework.data.datasource.SampleStringLocalDataSource
import com.w4eret1ckrtb1tch.homework.data.datasource.SampleStringRemoteDataSource
import com.w4eret1ckrtb1tch.homework.data.repository.SampleStringRepositoryImpl
import com.w4eret1ckrtb1tch.homework.domain.repository.SampleStringRepository
import dagger.Binds
import dagger.Module
import dagger.Reusable
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
interface DomainModule {

    @Binds
    @Reusable
    @LocalDataSource
    fun bindsStringLocalDataSource(
        stringLocalDataSource: SampleStringLocalDataSource
    ): SampleStringDataSource

    @Binds
    @Reusable
    @RemoteDataSource
    fun bindsStringRemoteDataSource(
        stringRemoteDataSource: SampleStringRemoteDataSource
    ): SampleStringDataSource

    @Binds
    @Singleton
    fun bindsStringRepository(stringRepository:SampleStringRepositoryImpl):SampleStringRepository

}

@Qualifier
annotation class LocalDataSource

@Qualifier
annotation class RemoteDataSource
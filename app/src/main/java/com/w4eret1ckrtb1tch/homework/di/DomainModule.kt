package com.w4eret1ckrtb1tch.homework.di

import com.w4eret1ckrtb1tch.homework.data.datasource.SampleStringDataSource
import com.w4eret1ckrtb1tch.homework.data.datasource.SampleStringLocalDataSource
import com.w4eret1ckrtb1tch.homework.data.datasource.SampleStringRemoteDataSource
import com.w4eret1ckrtb1tch.homework.data.repository.SampleStringRepositoryImpl
import com.w4eret1ckrtb1tch.homework.domain.repository.SampleStringRepository
import dagger.Binds
import dagger.Module
import javax.inject.Qualifier

@Module
interface DomainModule {

    @Binds
    @AppScope
    @LocalDataSource
    fun bindsStringLocalDataSource(
        stringLocalDataSource: SampleStringLocalDataSource
    ): SampleStringDataSource

    @Binds
    @AppScope
    @RemoteDataSource
    fun bindsStringRemoteDataSource(
        stringRemoteDataSource: SampleStringRemoteDataSource
    ): SampleStringDataSource

    @Binds
    @AppScope
    fun bindsStringRepository(stringRepository: SampleStringRepositoryImpl): SampleStringRepository

}

@Qualifier
annotation class LocalDataSource

@Qualifier
annotation class RemoteDataSource
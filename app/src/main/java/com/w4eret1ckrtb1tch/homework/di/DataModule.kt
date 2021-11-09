package com.w4eret1ckrtb1tch.homework.di

import com.w4eret1ckrtb1tch.homework.data.datasource.SampleStringDataSource
import com.w4eret1ckrtb1tch.homework.data.datasource.SampleStringLocalDataSource
import com.w4eret1ckrtb1tch.homework.data.datasource.SampleStringRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Reusable
import javax.inject.Qualifier

@Module
interface DataModule {

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

}

@Qualifier
annotation class LocalDataSource

@Qualifier
annotation class RemoteDataSource
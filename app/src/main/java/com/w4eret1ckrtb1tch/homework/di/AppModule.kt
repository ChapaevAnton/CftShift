package com.w4eret1ckrtb1tch.homework.di

import com.w4eret1ckrtb1tch.homework.data.dto.CurrenciesResponse
import com.w4eret1ckrtb1tch.homework.data.dto.CurrencyResponse
import com.w4eret1ckrtb1tch.homework.data.mapper.CurrenciesMapperImpl
import com.w4eret1ckrtb1tch.homework.data.repository.CurrenciesRepositoryImpl
import com.w4eret1ckrtb1tch.homework.data.repository.MockCurrenciesRepositoryImpl
import com.w4eret1ckrtb1tch.homework.domain.mapper.CurrenciesMapper
import com.w4eret1ckrtb1tch.homework.domain.repository.CurrenciesRepository
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier


@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Reusable
    abstract fun bindCurrenciesRepository(currenciesRepository: CurrenciesRepositoryImpl): CurrenciesRepository

    @Binds
    @Reusable
    @CurrentRepository
    abstract fun bindMockCurrenciesRepository(mockCurrenciesRepositoryImpl: MockCurrenciesRepositoryImpl): CurrenciesRepository

    @Binds
    @Reusable
    abstract fun bindCurrenciesMapper(
        currenciesMapper: CurrenciesMapperImpl
    ): @JvmSuppressWildcards CurrenciesMapper<CurrenciesResponse, CurrencyResponse>
}

@Qualifier
annotation class CurrentRepository

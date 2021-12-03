package com.maxsch.rxjavalecture.di

import com.maxsch.rxjavalecture.data.datasource.CatsApi
import com.maxsch.rxjavalecture.data.datasource.CatsApiImpl
import com.maxsch.rxjavalecture.data.datasource.PriceApi
import com.maxsch.rxjavalecture.data.datasource.PriceApiImpl
import com.maxsch.rxjavalecture.data.repository.CatsRepositoryImpl
import com.maxsch.rxjavalecture.data.repository.PriceRepositoryImpl
import com.maxsch.rxjavalecture.domain.repository.CatsRepository
import com.maxsch.rxjavalecture.domain.repository.PriceRepository
import com.maxsch.rxjavalecture.domain.usecase.GetCatsUseCase
import com.maxsch.rxjavalecture.domain.usecase.GetPriceUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface AppModule {

    @Binds
    fun bindsCatsApi(catsApi: CatsApiImpl): CatsApi

    @Binds
    fun bindsPriceApi(priceApi: PriceApiImpl): PriceApi

    @Binds
    fun bindsCatsRepository(catsRepository: CatsRepositoryImpl): CatsRepository

    @Binds
    fun bindsPriceRepository(priceRepository: PriceRepositoryImpl): PriceRepository

    companion object {
        @Provides
        fun providesGetCatsUseCase(catsRepository: CatsRepository): GetCatsUseCase =
            GetCatsUseCase(catsRepository)

        @Provides
        fun providesGetPriceUseCas(priceRepository: PriceRepository): GetPriceUseCase =
            GetPriceUseCase(priceRepository)
    }

}
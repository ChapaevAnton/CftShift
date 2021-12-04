package com.maxsch.rxjavalecture.di

import com.maxsch.rxjavalecture.data.datasource.*
import com.maxsch.rxjavalecture.data.repository.CatsRepositoryImpl
import com.maxsch.rxjavalecture.data.repository.DogsRepositoryImpl
import com.maxsch.rxjavalecture.data.repository.PriceRepositoryImpl
import com.maxsch.rxjavalecture.data.repository.RatsRepositoryImpl
import com.maxsch.rxjavalecture.domain.repository.CatsRepository
import com.maxsch.rxjavalecture.domain.repository.DogsRepository
import com.maxsch.rxjavalecture.domain.repository.PriceRepository
import com.maxsch.rxjavalecture.domain.repository.RatsRepository
import com.maxsch.rxjavalecture.domain.usecase.GetCatsUseCase
import com.maxsch.rxjavalecture.domain.usecase.GetDogsUseCase
import com.maxsch.rxjavalecture.domain.usecase.GetPriceUseCase
import com.maxsch.rxjavalecture.domain.usecase.GetRatsUseCase
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
    fun bindsDogsApi(dogsApi: DogsApiImpl): DogsApi

    @Binds
    fun bindsRatsApi(ratsApi: RatsApiImpl): RatsApi

    @Binds
    fun bindsCatsRepository(catsRepository: CatsRepositoryImpl): CatsRepository

    @Binds
    fun bindsPriceRepository(priceRepository: PriceRepositoryImpl): PriceRepository

    @Binds
    fun bindsDogsRepository(dogsRepository: DogsRepositoryImpl): DogsRepository

    @Binds
    fun bindsRatsRepository(ratsRepository: RatsRepositoryImpl): RatsRepository

    companion object {
        @Provides
        fun providesGetCatsUseCase(catsRepository: CatsRepository): GetCatsUseCase =
            GetCatsUseCase(catsRepository)

        @Provides
        fun providesGetPriceUseCas(priceRepository: PriceRepository): GetPriceUseCase =
            GetPriceUseCase(priceRepository)

        @Provides
        fun providesGetDogsUseCase(dogsRepository: DogsRepository): GetDogsUseCase =
            GetDogsUseCase(dogsRepository)

        @Provides
        fun providesGetRatsUseCase(ratsRepository: RatsRepository): GetRatsUseCase =
            GetRatsUseCase(ratsRepository)
    }

}
package com.maxsch.rxjavalecture.di

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
import org.koin.core.qualifier.named
import org.koin.dsl.module

val domainModule = module {

    single<CatsRepository> { CatsRepositoryImpl(get()) }
    single<DogsRepository> { DogsRepositoryImpl(get()) }
    single<RatsRepository> { RatsRepositoryImpl(get()) }
    single<PriceRepository> { PriceRepositoryImpl(get(qualifier = named("prod"))) }

    factory<GetCatsUseCase> { GetCatsUseCase(get()) }
    factory<GetDogsUseCase> { GetDogsUseCase(get()) }
    factory<GetRatsUseCase> { GetRatsUseCase(get()) }
    factory<GetPriceUseCase> { GetPriceUseCase(get()) }

}
package com.maxsch.rxjavalecture.di

import com.maxsch.rxjavalecture.data.datasource.*
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.dsl.single

val dataModule = module {

    single<CatsApiImpl>() bind CatsApi::class
    single<DogsApiImpl> { DogsApiImpl() } bind DogsApi::class
    single<RatsApi> { RatsApiImpl() as RatsApi }
    single<PriceApiProdImpl>(qualifier = named("prod")) bind PriceApi::class
    single<PriceApiDebugImpl>(qualifier = named("debug")) bind PriceApi::class

}
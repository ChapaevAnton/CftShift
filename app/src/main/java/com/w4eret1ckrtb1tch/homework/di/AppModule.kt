package com.w4eret1ckrtb1tch.homework.di

import com.w4eret1ckrtb1tch.homework.data.datasource.SourceModule
import com.w4eret1ckrtb1tch.homework.data.repository.ItemRepositoryImpl
import com.w4eret1ckrtb1tch.homework.domain.repository.ItemRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [SourceModule::class])
interface AppModule {

    @Singleton
    @Binds
    fun bindsItemRepository(itemRepository: ItemRepositoryImpl): ItemRepository

}
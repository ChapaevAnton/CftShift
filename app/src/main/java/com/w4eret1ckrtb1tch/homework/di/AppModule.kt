package com.w4eret1ckrtb1tch.homework.di

import com.w4eret1ckrtb1tch.homework.data.datasource.ItemDataSource
import com.w4eret1ckrtb1tch.homework.data.datasource.ItemDataSourceImpl
import com.w4eret1ckrtb1tch.homework.data.repository.ItemRepositoryImpl
import com.w4eret1ckrtb1tch.homework.domain.repository.ItemRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface AppModule {

    @Singleton
    @Binds
    fun bindsItemDataSource(itemDataSource: ItemDataSourceImpl): ItemDataSource

    @Singleton
    @Binds
    fun bindsItemRepository(itemRepository: ItemRepositoryImpl): ItemRepository

}
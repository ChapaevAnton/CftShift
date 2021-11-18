package com.w4eret1ckrtb1tch.homework.data.datasource

import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface SourceModule {

    @Singleton
    @Binds
    fun bindsItemDataSource(itemDataSource: ItemDataSourceDevImpl): ItemDataSource
}
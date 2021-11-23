package com.w4eret1ckrtb1tch.homework.di

import com.w4eret1ckrtb1tch.homework.data.datasource.local.ContentDataSource
import com.w4eret1ckrtb1tch.homework.data.datasource.local.ContentDataSourceImpl
import com.w4eret1ckrtb1tch.homework.data.repository.UploadRepositoryImpl
import com.w4eret1ckrtb1tch.homework.domain.repository.UploadRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface AppModule {

    @Binds
    fun bindsUploadRepository(UploadRepository: UploadRepositoryImpl): UploadRepository

    @Binds
    fun bindsContentDataSource(contentDataSource: ContentDataSourceImpl): ContentDataSource

}
package com.w4eret1ckrtb1tch.homework.di

import com.w4eret1ckrtb1tch.homework.domain.repository.AuthRepository
import com.w4eret1ckrtb1tch.homework.domain.usecase.PostLoginUserCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun providesPostLoginUserCase(repository: AuthRepository): PostLoginUserCase =
        PostLoginUserCase(repository)
}
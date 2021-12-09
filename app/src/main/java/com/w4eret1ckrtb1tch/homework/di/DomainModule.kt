package com.w4eret1ckrtb1tch.homework.di

import com.w4eret1ckrtb1tch.homework.domain.repository.AuthRepository
import com.w4eret1ckrtb1tch.homework.domain.repository.LoanRepository
import com.w4eret1ckrtb1tch.homework.domain.usecase.GetLoansListUseCase
import com.w4eret1ckrtb1tch.homework.domain.usecase.PostLoginUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun providesPostLoginUserCase(repository: AuthRepository): PostLoginUseCase =
        PostLoginUseCase(repository)

    @Provides
    fun providesGetLoansListUseCase(repository: LoanRepository): GetLoansListUseCase =
        GetLoansListUseCase(repository)
}
package com.w4eret1ckrtb1tch.homework.di

import com.w4eret1ckrtb1tch.homework.domain.repository.AuthRepository
import com.w4eret1ckrtb1tch.homework.domain.repository.LoanRepository
import com.w4eret1ckrtb1tch.homework.domain.storage.PreferenceStorage
import com.w4eret1ckrtb1tch.homework.domain.usecase.GetLoansListUseCase
import com.w4eret1ckrtb1tch.homework.domain.usecase.PostLoginUserUseCase
import com.w4eret1ckrtb1tch.homework.domain.usecase.PostRegisterUserUseCase
import com.w4eret1ckrtb1tch.homework.domain.usecase.WriteAuthTokenUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    fun providesPostLoginUserCase(repository: AuthRepository): PostLoginUserUseCase =
        PostLoginUserUseCase(repository)

    @Provides
    fun providesPostRegisterUserUseCase(repository: AuthRepository): PostRegisterUserUseCase =
        PostRegisterUserUseCase(repository)

    @Provides
    fun providesGetLoansListUseCase(repository: LoanRepository): GetLoansListUseCase =
        GetLoansListUseCase(repository)

    @Provides
    fun providesWriteAuthTokenUseCase(preferenceStorage: PreferenceStorage): WriteAuthTokenUseCase =
        WriteAuthTokenUseCase(preferenceStorage)
}
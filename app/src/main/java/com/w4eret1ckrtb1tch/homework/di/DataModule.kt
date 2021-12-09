package com.w4eret1ckrtb1tch.homework.di

import com.w4eret1ckrtb1tch.homework.data.mapper.LoanResponseMapperImpl
import com.w4eret1ckrtb1tch.homework.data.mapper.UserResponseMapperImpl
import com.w4eret1ckrtb1tch.homework.data.repository.AuthRepositoryImpl
import com.w4eret1ckrtb1tch.homework.data.repository.LoanRepositoryImpl
import com.w4eret1ckrtb1tch.homework.domain.mapper.LoanResponseMapper
import com.w4eret1ckrtb1tch.homework.domain.mapper.UserResponseMapper
import com.w4eret1ckrtb1tch.homework.domain.repository.AuthRepository
import com.w4eret1ckrtb1tch.homework.domain.repository.LoanRepository
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Reusable
    @Binds
    fun bindsUserResponseMapper(userResponseMapper: UserResponseMapperImpl): UserResponseMapper

    @Reusable
    @Binds
    fun bindsLoanResponseMapper(loanResponseMapper: LoanResponseMapperImpl): LoanResponseMapper

    @Reusable
    @Binds
    fun bindsAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository

    @Reusable
    @Binds
    fun bindsLoanRepository(loanRepository: LoanRepositoryImpl): LoanRepository
}
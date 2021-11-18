package com.w4eret1ckrtb1tch.homework.di

import androidx.lifecycle.ViewModelProvider
import com.w4eret1ckrtb1tch.homework.presentation.utils.ViewModelFactoryImpl
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindsViewModelFactory(viewModelFactory: ViewModelFactoryImpl): ViewModelProvider.Factory

}
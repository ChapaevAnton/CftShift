package com.w4eret1ckrtb1tch.homework.di

import androidx.lifecycle.ViewModelProvider
import com.w4eret1ckrtb1tch.homework.presentation.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelBuilder {

    @Binds
    @AppScope
    fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
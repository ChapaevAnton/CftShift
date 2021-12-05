package com.maxsch.rxjavalecture.di

import com.maxsch.rxjavalecture.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        MainViewModel(
            getCatsUseCase = get(),
            getDogsUseCase = get(),
            getRatsUseCase = get(),
            getPriceUseCase = get()
        )
    }
}
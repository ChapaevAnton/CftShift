package com.w4eret1ckrtb1tch.homework.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.w4eret1ckrtb1tch.homework.presentation.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
interface PresentationModule {

    @Binds
    @Singleton
    fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @[IntoMap ViewModelKey(MainViewModel::class)]
    fun bindsMainViewModel(mainViewModel: MainViewModel): ViewModel

}
package com.w4eret1ckrtb1tch.homework.di

import androidx.lifecycle.ViewModel
import com.w4eret1ckrtb1tch.homework.presentation.ScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FragmentModule {

    @Binds
    @FragmentScope
    @[IntoMap ViewModelKey(ScreenViewModel::class)]
    fun bindsScreenViewModel(screenViewModel: ScreenViewModel): ViewModel

}
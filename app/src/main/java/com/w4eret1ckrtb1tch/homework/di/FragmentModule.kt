package com.w4eret1ckrtb1tch.homework.di

import androidx.lifecycle.ViewModel
import com.w4eret1ckrtb1tch.homework.presentation.ScreenFragment
import com.w4eret1ckrtb1tch.homework.presentation.ScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface FragmentModule {

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    fun screenFragment(): ScreenFragment

    @FragmentScope
    @Binds
    @[IntoMap ViewModelKey(ScreenViewModel::class)]
    fun bindsScreenViewModel(screenViewModel: ScreenViewModel): ViewModel
}
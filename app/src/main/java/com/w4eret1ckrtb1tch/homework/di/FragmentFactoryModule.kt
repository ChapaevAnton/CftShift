package com.w4eret1ckrtb1tch.homework.di

import androidx.fragment.app.FragmentFactory
import com.w4eret1ckrtb1tch.homework.presentation.utils.FragmentFactoryImpl
import dagger.Binds
import dagger.Module

@Module
interface FragmentFactoryModule {

    @Binds
    fun bindsFragmentFactor(fragmentFactory: FragmentFactoryImpl): FragmentFactory
}
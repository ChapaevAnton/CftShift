package com.w4eret1ckrtb1tch.homework.di

import com.w4eret1ckrtb1tch.homework.MainActivity
import com.w4eret1ckrtb1tch.homework.presentation.ScreenFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface BuildersModule {

    @ActivityScope
    @ContributesAndroidInjector
    fun contributesMainActivity(): MainActivity

    @FragmentScope
    @ContributesAndroidInjector
    fun contributesScreenFragment(): ScreenFragment

}
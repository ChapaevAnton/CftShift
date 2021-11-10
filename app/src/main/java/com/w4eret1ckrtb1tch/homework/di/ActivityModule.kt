package com.w4eret1ckrtb1tch.homework.di

import com.w4eret1ckrtb1tch.homework.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            ViewModelBuilder::class
        ]
    )
    fun mainActivity(): MainActivity

}
package com.w4eret1ckrtb1tch.homework.di

import com.w4eret1ckrtb1tch.homework.presentation.ScreenFragment
import dagger.Subcomponent

@FragmentScope
@Subcomponent(
    modules = [
        FragmentModule::class
    ]
)
interface FragmentComponent {


    @Subcomponent.Builder
    interface Builder {

        fun build(): FragmentComponent
    }

    fun inject(fragment: ScreenFragment)
}
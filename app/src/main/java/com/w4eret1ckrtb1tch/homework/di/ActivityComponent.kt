package com.w4eret1ckrtb1tch.homework.di

import com.w4eret1ckrtb1tch.homework.MainActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(
    modules = []
)
interface ActivityComponent {

    @Subcomponent.Builder
    interface Builder {

        fun build(): ActivityComponent
    }

    fun inject(activity: MainActivity)

}
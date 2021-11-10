package com.w4eret1ckrtb1tch.homework.di

import dagger.Module

@Module(
    subcomponents = [
        ActivityComponent::class,
        FragmentComponent::class
    ]
)
interface AppSubcomponent
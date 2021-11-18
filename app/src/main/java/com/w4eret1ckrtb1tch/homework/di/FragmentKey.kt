package com.w4eret1ckrtb1tch.homework.di

import androidx.fragment.app.Fragment
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
annotation class FragmentKey(val value: KClass<out Fragment>)
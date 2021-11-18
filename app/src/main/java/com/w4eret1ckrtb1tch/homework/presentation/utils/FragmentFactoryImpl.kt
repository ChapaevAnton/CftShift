package com.w4eret1ckrtb1tch.homework.presentation.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject

class FragmentFactoryImpl @Inject constructor(
    private val fragments: @JvmSuppressWildcards Map<Class<out Fragment>, Fragment>
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment =
        fragments[Class.forName(className)] as Fragment
}
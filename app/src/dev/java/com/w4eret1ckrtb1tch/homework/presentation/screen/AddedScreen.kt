package com.w4eret1ckrtb1tch.homework.presentation.screen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.w4eret1ckrtb1tch.homework.presentation.fragment.added.AddedFragment

object AddedScreen {

    fun createAddedFragment(factory: FragmentFactory): Fragment {
        return factory.instantiate(
            ClassLoader.getSystemClassLoader(),
            AddedFragment::class.java.name
        )
    }
}
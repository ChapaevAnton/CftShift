package com.w4eret1ckrtb1tch.homework.presentation.screen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.w4eret1ckrtb1tch.homework.ui.fragment.list.ListFragment

object MainScreen {

    fun createListFragment(factory: FragmentFactory): Fragment {
        return factory.instantiate(
            ClassLoader.getSystemClassLoader(),
            ListFragment::class.java.name
        )
    }

}
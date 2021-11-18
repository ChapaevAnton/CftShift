package com.w4eret1ckrtb1tch.homework.presentation.utils

import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.commit
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.presentation.screen.AddedScreen
import com.w4eret1ckrtb1tch.homework.presentation.screen.MainScreen
import com.w4eret1ckrtb1tch.homework.ui.activity.MainActivity
import javax.inject.Inject

class FragmentRouterImpl @Inject constructor(
    private val fragmentFactory: FragmentFactory
) : FragmentRouter {

    override fun openAddedFragment(
        activity: MainActivity,
        bundle: Bundle?
    ) {
        val fragment = AddedScreen.createAddedFragment(fragmentFactory).apply {
            arguments = bundle
        }
        activity.supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container, fragment)
            addToBackStack(fragment::class.simpleName)
        }
    }

    override fun openListFragment(
        activity: MainActivity,
        bundle: Bundle?
    ) {
        val fragment = MainScreen.createListFragment(fragmentFactory).apply {
            arguments = bundle
        }
        activity.supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.fragment_container, fragment)
        }
    }
}
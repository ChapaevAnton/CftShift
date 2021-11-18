package com.w4eret1ckrtb1tch.homework.presentation.utils

import android.os.Bundle
import com.w4eret1ckrtb1tch.homework.ui.activity.MainActivity

interface FragmentRouter {

    fun openAddedFragment(
        activity: MainActivity,
        bundle: Bundle?
    )

    fun openListFragment(
        activity: MainActivity,
        bundle: Bundle?
    )
}
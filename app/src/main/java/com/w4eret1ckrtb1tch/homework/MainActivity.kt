package com.w4eret1ckrtb1tch.homework

import android.graphics.Color
import androidx.fragment.app.Fragment

class MainActivity : BaseActivity() {

    override fun createFragment(): Fragment = BaseFragment.newInstance<FragmentA>(Color.RED)

}

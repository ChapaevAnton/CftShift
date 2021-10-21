package com.w4eret1ckrtb1tch.homework

import android.graphics.Color

class FragmentB : BaseFragment(R.layout.fragment_layout) {

    override val color: Int?
        get() = arguments?.getInt(getKey<FragmentB>())

    override val name: String?
        get() = this::class.simpleName

    override fun startFragment() {
        val color = Color.GREEN
        val fragment = newInstance<FragmentC>(color)
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment).addToBackStack(name).commit()
    }

}
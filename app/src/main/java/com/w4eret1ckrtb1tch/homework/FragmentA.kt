package com.w4eret1ckrtb1tch.homework

import android.graphics.Color

class FragmentA : BaseFragment(R.layout.fragment_layout) {

    override val color: Int?
        get() = arguments?.getInt(getKey<FragmentA>())

    override val name: String?
        get() = this::class.simpleName

    override fun startFragment() {
        val color = Color.YELLOW
        val fragment = newInstance<FragmentB>(color)
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment).addToBackStack(name).commit()
    }

}
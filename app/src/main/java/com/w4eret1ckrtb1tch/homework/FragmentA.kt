package com.w4eret1ckrtb1tch.homework

import android.graphics.Color
import android.os.Bundle

class FragmentA : BaseFragment(R.layout.fragment_layout) {

    override val color: Int?
        get() = arguments?.getInt(COLOR_PARAM)

    override val name: String?
        get() = this::class.simpleName

    override fun startFragment() {
        val color = Color.YELLOW
        val fragment = FragmentB.newInstance(color)
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment).addToBackStack(name).commit()
    }

    companion object {
        fun newInstance(color: Int): FragmentA = FragmentA().apply {
            arguments = Bundle().apply {
                putInt(COLOR_PARAM, color)
            }
        }

        private const val COLOR_PARAM = "com.homework.FragmentA.COLOR_PARAM"
    }

}
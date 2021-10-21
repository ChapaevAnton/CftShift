package com.w4eret1ckrtb1tch.homework

import android.os.Bundle
import androidx.fragment.app.FragmentManager

class FragmentC : BaseFragment(R.layout.fragment_layout) {

    override val color: Int?
        get() = arguments?.getInt(COLOR_PARAM)
    override val name: String?
        get() = this::class.simpleName

    override fun startFragment() {
        with(requireActivity().supportFragmentManager) {
            popBackStack(
                getBackStackEntryAt(0).name,
                FragmentManager.POP_BACK_STACK_INCLUSIVE
            )
        }
    }

    companion object {
        fun newInstance(color: Int): FragmentC = FragmentC().apply {
            arguments = Bundle().apply {
                putInt(COLOR_PARAM, color)
            }
        }

        private const val COLOR_PARAM = "com.homework.FragmentC.COLOR_PARAM"
    }
}
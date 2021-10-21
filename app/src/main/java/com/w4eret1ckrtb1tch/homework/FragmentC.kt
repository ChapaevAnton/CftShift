package com.w4eret1ckrtb1tch.homework

import androidx.fragment.app.FragmentManager

class FragmentC : BaseFragment(R.layout.fragment_layout) {

    override val color: Int?
        get() = arguments?.getInt(getKey<FragmentC>())
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

}
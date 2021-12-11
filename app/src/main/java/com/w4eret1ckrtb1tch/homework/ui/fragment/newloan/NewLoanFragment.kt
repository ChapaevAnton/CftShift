package com.w4eret1ckrtb1tch.homework.ui.fragment.newloan

import android.os.Bundle
import android.view.View
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.databinding.FragmentNewLoanBinding
import com.w4eret1ckrtb1tch.homework.ui.fragment.BaseFragment

class NewLoanFragment : BaseFragment<FragmentNewLoanBinding>(
    FragmentNewLoanBinding::inflate,
    R.layout.fragment_new_loan
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}
package com.w4eret1ckrtb1tch.homework.ui.fragment.newloan

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.databinding.FragmentNewLoanBinding
import com.w4eret1ckrtb1tch.homework.presentation.viewmodel.NewLoanViewModel
import com.w4eret1ckrtb1tch.homework.ui.fragment.BaseFragment

class NewLoanFragment : BaseFragment<FragmentNewLoanBinding>(
    FragmentNewLoanBinding::inflate,
    R.layout.fragment_new_loan
) {

    private val viewModel by viewModels<NewLoanViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            sendNewLoan.setOnClickListener {  }
        }
    }
}
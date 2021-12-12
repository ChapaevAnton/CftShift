package com.w4eret1ckrtb1tch.homework.ui.fragment.newloan

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.databinding.FragmentNewLoanBinding
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanConditions
import com.w4eret1ckrtb1tch.homework.domain.entity.Result
import com.w4eret1ckrtb1tch.homework.presentation.viewmodel.NewLoanViewModel
import com.w4eret1ckrtb1tch.homework.ui.activity.MainActivity
import com.w4eret1ckrtb1tch.homework.ui.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewLoanFragment : BaseFragment<FragmentNewLoanBinding>(
    FragmentNewLoanBinding::inflate,
    R.layout.fragment_new_loan
) {

    private val viewModel by viewModels<NewLoanViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            sendNewLoan.setOnClickListener { createLoan() }
        }
        viewModel.getConditions.observe(viewLifecycleOwner) { resolveLoanConditions(it) }
    }

    private fun resolveLoanConditions(result: Result<LoanConditions>) {
        when (result) {
            is Result.Success -> {
                setLoanConditions(result.value)
            }
            is Result.Failure -> {
                (requireActivity() as MainActivity).showMessage(
                    result.toString(),
                    binding.root,
                    null
                )
            }
            is Result.Loading -> {
                // TODO: 07.12.2021 Добавить индикатор загрузки
            }
        }
    }

    private fun setLoanConditions(loanConditions: LoanConditions) {
        binding.apply {
            percent.text = loanConditions.percent.toString()
            period.text = loanConditions.period.toString()
            loanConditions.maxAmount.toString().also { maxAmount.text = it }
        }
    }

    private fun createLoan() {
        binding.apply {
            val firstName = firstName.editText?.text.toString()
            val lastName = lastName.editText?.text.toString()
            val phoneNumber = phoneNumber.editText?.text.toString()
            val amount = amount.editText?.text.toString().toLong()
            viewModel.createLoan(firstName, lastName, phoneNumber, amount)
        }
    }
}
package com.w4eret1ckrtb1tch.homework.ui.fragment.newloan

import android.os.Bundle
import android.view.View
import androidx.core.widget.doBeforeTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.databinding.FragmentNewLoanBinding
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanConditions
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanEntity
import com.w4eret1ckrtb1tch.homework.presentation.model.InputFieldError
import com.w4eret1ckrtb1tch.homework.presentation.model.Result
import com.w4eret1ckrtb1tch.homework.presentation.utils.ResolveResultHelper
import com.w4eret1ckrtb1tch.homework.presentation.viewmodel.NewLoanViewModel
import com.w4eret1ckrtb1tch.homework.ui.fragment.BaseFragment
import com.w4eret1ckrtb1tch.homework.ui.utils.showMessage
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
            firstName.editText?.doBeforeTextChanged { _, _, _, _ -> firstName.error = null }
            lastName.editText?.doBeforeTextChanged { _, _, _, _ -> lastName.error = null }
            phoneNumber.editText?.doBeforeTextChanged { _, _, _, _ -> phoneNumber.error = null }
            amount.editText?.doBeforeTextChanged { _, _, _, _ -> amount.error = null }
        }
        viewModel.getConditions.observe(viewLifecycleOwner) { resolveLoanConditions(it) }
        viewModel.getLoanInfo.observe(viewLifecycleOwner) { resolveLoanInfo(it) }
        viewModel.getIncorrectInputField.observe(viewLifecycleOwner) { validateInputField(it) }
    }

    private fun resolveLoanConditions(result: Result<LoanConditions>) {
        ResolveResultHelper.resolveResult(result,
            success = { setLoanConditions(it);loading(false) },
            failure = { showMessage(it, binding.root, null);loading(false) },
            loading = { loading(true) })
    }

    private fun resolveLoanInfo(result: Result<LoanEntity>) {
        ResolveResultHelper.resolveResult(result,
            success = { openNewLoanToSendLoan(it);loading(false) },
            failure = { showMessage(it, binding.root, null);loading(false) },
            loading = { loading(true) })
    }

    private fun loading(load: Boolean) {
        binding.loading.visibility = if (load) View.VISIBLE else View.GONE
    }

    private fun setLoanConditions(loanConditions: LoanConditions) {
        binding.apply {
            percent.text = getString(R.string.percent_data, loanConditions.percent)
            period.text = getString(R.string.period_data, loanConditions.period)
            maxAmount.text = getString(R.string.max_amount_data, loanConditions.maxAmount)
        }
    }

    private fun createLoan() {
        binding.apply {
            val firstName = firstName.editText?.text.toString()
            val lastName = lastName.editText?.text.toString()
            val phoneNumber = phoneNumber.editText?.text.toString()
            val amount = amount.editText?.text.toString()
            viewModel.createLoan(firstName, lastName, phoneNumber, amount)
        }
    }

    private fun validateInputField(inputFieldError: InputFieldError) {
        when (inputFieldError) {
            InputFieldError.FIRST_NAME_EMPTY -> {
                binding.firstName.error = getString(R.string.first_name_empty)
            }
            InputFieldError.LAST_NAME_EMPTY -> {
                binding.lastName.error = getString(R.string.last_name_empty)
            }
            InputFieldError.PHONE_NUMBER_EMPTY -> {
                binding.phoneNumber.error = getString(R.string.phone_number_empty)
            }
            InputFieldError.AMOUNT_EMPTY -> {
                binding.amount.error = getString(R.string.amount_empty)
            }
            InputFieldError.AMOUNT_MAX_VALUE -> {
                binding.amount.error = getString(R.string.amount_max_value)
            }
            InputFieldError.AMOUNT_ZERO -> {
                binding.amount.error = getString(R.string.amount_zero)
            }
            else -> {
                showMessage(getString(R.string.unknown_error), binding.root, null)
            }

        }
    }

    private fun openNewLoanToSendLoan(loanEntity: LoanEntity) {
        val action = NewLoanFragmentDirections.actionNewLoanToSendLoan(loanEntity)
        findNavController().navigate(action)
    }
}
package com.w4eret1ckrtb1tch.homework.ui.fragment.loandata

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.databinding.FragmentLoanDataBinding
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanEntity
import com.w4eret1ckrtb1tch.homework.presentation.utils.Result
import com.w4eret1ckrtb1tch.homework.presentation.viewmodel.LoanDataViewModel
import com.w4eret1ckrtb1tch.homework.ui.activity.MainActivity
import com.w4eret1ckrtb1tch.homework.ui.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoanDataFragment : BaseFragment<FragmentLoanDataBinding>(
    FragmentLoanDataBinding::inflate,
    R.layout.fragment_loan_data
) {

    private val viewModel by viewModels<LoanDataViewModel>()
    private val args: LoanDataFragmentArgs by navArgs()
    private var idLoan: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idLoan = args.idLoan
        idLoan?.let { viewModel.getLoanData(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLoan.observe(viewLifecycleOwner) { resolveLoanData(it) }
    }

    private fun resolveLoanData(result: Result<LoanEntity>) {
        when (result) {
            is Result.Success -> {
                setLoanData(result.value)
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

    private fun setLoanData(loan: LoanEntity) {
        binding.apply {
            state.text = loan.state.name
            idLoan.text = loan.id.toString()
            date.text = loan.date.toString()
            firstName.text = loan.firstName
            lastName.text = loan.lastName
            phoneNumber.text = loan.phoneNumber
            period.text = loan.period.toString()
            percent.text = loan.percent.toString()
            loan.amount.toString().also { amount.text = it }
        }
    }
}
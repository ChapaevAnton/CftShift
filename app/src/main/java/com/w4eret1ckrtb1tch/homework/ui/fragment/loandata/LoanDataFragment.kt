package com.w4eret1ckrtb1tch.homework.ui.fragment.loandata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.databinding.FragmentLoanDataBinding
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanEntity
import com.w4eret1ckrtb1tch.homework.domain.entity.Result
import com.w4eret1ckrtb1tch.homework.presentation.LoanDataViewModel
import com.w4eret1ckrtb1tch.homework.ui.activity.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoanDataFragment : Fragment(R.layout.fragment_loan_data) {

    private var binding: FragmentLoanDataBinding? = null
    private val viewModel by viewModels<LoanDataViewModel>()
    private val args: LoanDataFragmentArgs by navArgs()
    private var idLoan: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        idLoan = args.idLoan
        idLoan?.let { viewModel.getLoanData(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoanDataBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLoan.observe(viewLifecycleOwner) { resolveLoanData(it) }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun resolveLoanData(result: Result<LoanEntity>) {
        when (result) {
            is Result.Success -> {
                setLoanData(result.value)
            }
            is Result.Failure -> {
                (requireActivity() as MainActivity).showMessage(
                    result.toString(),
                    binding?.root!!,
                    null
                )
            }
            is Result.Loading -> {
                // TODO: 07.12.2021 Добавить индикатор загрузки
            }
        }
    }

    private fun setLoanData(loan: LoanEntity) {
        binding?.apply {
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
package com.w4eret1ckrtb1tch.homework.ui.fragment.loandata

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.databinding.FragmentLoanDataBinding
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanEntity
import com.w4eret1ckrtb1tch.homework.presentation.model.Result
import com.w4eret1ckrtb1tch.homework.presentation.utils.ResolveResultHelper
import com.w4eret1ckrtb1tch.homework.presentation.viewmodel.LoanDataViewModel
import com.w4eret1ckrtb1tch.homework.ui.fragment.BaseFragment
import com.w4eret1ckrtb1tch.homework.ui.utils.showMessage
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
        ResolveResultHelper.resolveResult(result,
            success = { setLoanData(it);loading(false) },
            failure = { showMessage(it, binding.root, null);loading(false) },
            loading = { loading(true) }
        )
    }

    private fun loading(load: Boolean) {
        binding.loading.visibility = if (load) View.VISIBLE else View.GONE
    }

    private fun setLoanData(loan: LoanEntity) {
        binding.apply {
            state.text = getString(R.string.state_data, loan.state.name)
            idLoan.text = getString(R.string.id_loan_data, loan.id)
            date.text = getString(R.string.date_data, loan.date)
            firstName.text = getString(R.string.first_name_data, loan.firstName)
            lastName.text = getString(R.string.last_name_data, loan.lastName)
            phoneNumber.text = getString(R.string.phone_number_data, loan.phoneNumber)
            period.text = getString(R.string.period_data, loan.period)
            percent.text = getString(R.string.percent_data, loan.percent)
            amount.text = getString(R.string.amount_data, loan.amount)
        }
    }
}
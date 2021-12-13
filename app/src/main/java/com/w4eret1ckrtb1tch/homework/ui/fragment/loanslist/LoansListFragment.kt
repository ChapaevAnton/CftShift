package com.w4eret1ckrtb1tch.homework.ui.fragment.loanslist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.databinding.FragmentLoansListBinding
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanEntity
import com.w4eret1ckrtb1tch.homework.presentation.model.Result
import com.w4eret1ckrtb1tch.homework.presentation.utils.ResolveResultHelper
import com.w4eret1ckrtb1tch.homework.presentation.viewmodel.LoansListViewModel
import com.w4eret1ckrtb1tch.homework.ui.fragment.BaseFragment
import com.w4eret1ckrtb1tch.homework.ui.utils.showMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoansListFragment : BaseFragment<FragmentLoansListBinding>(
    FragmentLoansListBinding::inflate,
    R.layout.fragment_loans_list
) {

    private val viewModel by viewModels<LoansListViewModel>()
    private lateinit var adapter: LoansAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = LoansAdapter { idLoan -> idLoan?.let { openLoanListToLoanData(it) } }
        binding.apply {
            if (this@LoansListFragment::adapter.isInitialized) loansList.adapter = adapter
            createNewLoan.setOnClickListener { openLoanListToNewLoan() }
        }
        viewModel.getLoans.observe(viewLifecycleOwner) { resolveLoansList(it) }
    }

    private fun resolveLoansList(result: Result<List<LoanEntity>>) {
        ResolveResultHelper.resolveResult(result,
            success = {
                if (this@LoansListFragment::adapter.isInitialized) adapter.loans = it;loading(false)
            },
            failure = {
                showMessage(it, binding.root, null)
                loading(false)
            },
            loading = { loading(true) })
    }

    private fun loading(load: Boolean) {
        binding.apply {
            loading.visibility = if (load) View.VISIBLE else View.GONE
            loansList.visibility = if (load) View.GONE else View.VISIBLE
            createNewLoan.visibility = if (load) View.GONE else View.VISIBLE
        }
    }

    private fun openLoanListToLoanData(idLoan: Long) {
        val action = LoansListFragmentDirections.actionLoanListToLoanData(idLoan)
        findNavController().navigate(action)
    }

    private fun openLoanListToNewLoan() {
        val action = LoansListFragmentDirections.actionLoanListToNewLoan()
        findNavController().navigate(action)
    }
}
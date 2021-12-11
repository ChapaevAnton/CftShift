package com.w4eret1ckrtb1tch.homework.ui.fragment.loanslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.databinding.FragmentLoansListBinding
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanEntity
import com.w4eret1ckrtb1tch.homework.domain.entity.Result
import com.w4eret1ckrtb1tch.homework.presentation.LoansListViewModel
import com.w4eret1ckrtb1tch.homework.ui.activity.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoansListFragment : Fragment(R.layout.fragment_loans_list) {

    private var binding: FragmentLoansListBinding? = null
    private val viewModel by viewModels<LoansListViewModel>()
    private lateinit var adapter: LoansAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoansListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = LoansAdapter { idLoan -> idLoan?.let { openLoanListToLoanData(it) } }

        binding?.apply {
            if (this@LoansListFragment::adapter.isInitialized) loansList.adapter = adapter
        }
        viewModel.getLoans.observe(viewLifecycleOwner) { resolveLoansList(it) }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun resolveLoansList(result: Result<List<LoanEntity>>) {
        when (result) {
            is Result.Success -> {
                if (this@LoansListFragment::adapter.isInitialized) adapter.loans = result.value
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

    private fun openLoanListToLoanData(idLoan: Long) {
        val action = LoansListFragmentDirections.actionLoanListToLoanData(idLoan)
        findNavController().navigate(action)
    }
}
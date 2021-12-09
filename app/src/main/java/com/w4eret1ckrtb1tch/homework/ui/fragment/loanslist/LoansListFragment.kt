package com.w4eret1ckrtb1tch.homework.ui.fragment.loanslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
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
    private val args: LoansListFragmentArgs? by navArgs()
    private var authToken: String? = null
    private val adapter by lazy { LoansAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args?.let {
            authToken = it.authToken
        }
        authToken?.let { viewModel.getLoansList(it) }
    }

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
        binding?.apply {
            loansList.adapter = adapter
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
                adapter.loans = result.value
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
}
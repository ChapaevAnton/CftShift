package com.w4eret1ckrtb1tch.homework.ui.fragment.loanslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.databinding.FragmentLoansListBinding
import com.w4eret1ckrtb1tch.homework.presentation.LoansListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoansListFragment : Fragment(R.layout.fragment_loans_list) {

    private var binding: FragmentLoansListBinding? = null
    private val viewModel by viewModels<LoansListViewModel>()
    private val adapter by lazy { LoansAdapter() }

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
            viewModel.getLoans.observe(viewLifecycleOwner) { loans -> adapter.loans = loans }
        }
    }
}
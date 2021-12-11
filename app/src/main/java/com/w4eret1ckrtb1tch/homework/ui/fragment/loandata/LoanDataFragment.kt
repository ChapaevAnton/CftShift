package com.w4eret1ckrtb1tch.homework.ui.fragment.loandata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.databinding.FragmentLoanDataBinding
import com.w4eret1ckrtb1tch.homework.presentation.LoanDataViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoanDataFragment : Fragment(R.layout.fragment_loan_data) {

    private var binding: FragmentLoanDataBinding? = null
    private val viewModel by viewModels<LoanDataViewModel>()

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
        binding?.apply {

        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}
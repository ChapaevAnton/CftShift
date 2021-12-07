package com.w4eret1ckrtb1tch.homework.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.databinding.FragmentLoanListBinding

class LoanListFragment : Fragment(R.layout.fragment_loan_list) {

    private var binding: FragmentLoanListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoanListBinding.inflate(inflater, container, false)
        return binding?.root
    }
}
package com.w4eret1ckrtb1tch.homework.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.databinding.FragmentAuthorizationBinding
import com.w4eret1ckrtb1tch.homework.presentation.AuthorizationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthorizationFragment : Fragment(R.layout.fragment_authorization) {

    private var binding: FragmentAuthorizationBinding? = null
    private val viewModel by viewModels<AuthorizationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthorizationBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            viewModel.loginUser()

            viewModel.getHash.observe(viewLifecycleOwner) { hashResult ->
                Log.d("TAG", "onViewCreated: $hashResult")
                info.text = hashResult
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}
package com.w4eret1ckrtb1tch.homework.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.databinding.FragmentUserRegistrationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationUserFragment : Fragment(R.layout.fragment_user_registration) {

    private var binding: FragmentUserRegistrationBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserRegistrationBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            register.setOnClickListener {
                // TODO: 07.12.2021 Запрос на сервер для регистрации
                openAuthUserToLoanList()
            }
        }
    }

    private fun openAuthUserToLoanList() {
        val action = RegistrationUserFragmentDirections.actionRegUserToAuthUser()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}
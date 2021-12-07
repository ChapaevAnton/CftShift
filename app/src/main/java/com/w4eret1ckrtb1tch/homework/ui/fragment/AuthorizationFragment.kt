package com.w4eret1ckrtb1tch.homework.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.databinding.FragmentAuthorizationBinding
import com.w4eret1ckrtb1tch.homework.domain.entity.Result
import com.w4eret1ckrtb1tch.homework.presentation.AuthorizationViewModel
import com.w4eret1ckrtb1tch.homework.ui.activity.MainActivity
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
            signIn.setOnClickListener { signInUser() }


        }

        viewModel.getHash.observe(viewLifecycleOwner) { userAuthorization(it) }
    }

    private fun signInUser() {
        // FIXME: 07.12.2021 Контроль ввода данных в поля
        binding?.apply {
            val name = userLogin.editText?.text.toString()
            val password = userPassword.editText?.text.toString()
            viewModel.signInUser(name, password)
        }
    }

    private fun userAuthorization(result: Result<String>) {
        when (result) {
            is Result.Success -> {
                TODO()
            }
            is Result.Failure -> {
                (requireActivity() as MainActivity).showMessage(
                    result.toString(),
                    binding?.root!!,
                    null
                )
            }
            is Result.Loading -> {

            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}
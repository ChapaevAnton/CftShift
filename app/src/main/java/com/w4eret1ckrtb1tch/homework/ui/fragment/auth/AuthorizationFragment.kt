package com.w4eret1ckrtb1tch.homework.ui.fragment.auth

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.databinding.FragmentAuthorizationBinding
import com.w4eret1ckrtb1tch.homework.presentation.utils.Result
import com.w4eret1ckrtb1tch.homework.domain.entity.UserEntity
import com.w4eret1ckrtb1tch.homework.presentation.viewmodel.AuthorizationViewModel
import com.w4eret1ckrtb1tch.homework.ui.activity.MainActivity
import com.w4eret1ckrtb1tch.homework.ui.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthorizationFragment : BaseFragment<FragmentAuthorizationBinding>(
    FragmentAuthorizationBinding::inflate,
    R.layout.fragment_authorization
) {

    private val viewModel by viewModels<AuthorizationViewModel>()
    private val args: AuthorizationFragmentArgs? by navArgs()
    private var user: UserEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args?.let {
            user = it.user
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            user?.let { userLogin.editText?.setText(it.name) }
            signIn.setOnClickListener { signInUser() }
            registration.setOnClickListener { openAuthUserToRegUser() }
        }

        viewModel.getAuthToken.observe(viewLifecycleOwner) { resolveUserAuthorization(it) }
    }

    private fun signInUser() {
        // FIXME: 07.12.2021 Контроль ввода данных в поля
        val name = binding.userLogin.editText?.text.toString()
        val password = binding.userPassword.editText?.text.toString()
        viewModel.signInUser(name, password)
    }

    private fun resolveUserAuthorization(result: Result<Unit>) {
        when (result) {
            is Result.Success -> {
                openAuthUserToLoanList()
            }
            is Result.Failure -> {
                (requireActivity() as MainActivity).showMessage(
                    result.toString(),
                    binding.root,
                    null
                )
            }
            is Result.Loading -> {
                // TODO: 07.12.2021 Добавить индикатор загрузки
            }
        }
    }

    private fun openAuthUserToLoanList() {
        val action = AuthorizationFragmentDirections.actionAuthUserToLoanList()
        findNavController().navigate(action)
    }

    private fun openAuthUserToRegUser() {
        val action = AuthorizationFragmentDirections.actionAuthUserToRegUser()
        findNavController().navigate(action)
    }
}
package com.w4eret1ckrtb1tch.homework.ui.fragment.auth

import android.os.Bundle
import android.view.View
import androidx.core.widget.doBeforeTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.databinding.FragmentAuthorizationBinding
import com.w4eret1ckrtb1tch.homework.domain.entity.UserEntity
import com.w4eret1ckrtb1tch.homework.presentation.model.InputFieldError
import com.w4eret1ckrtb1tch.homework.presentation.model.Result
import com.w4eret1ckrtb1tch.homework.presentation.utils.ResolveResultHelper
import com.w4eret1ckrtb1tch.homework.presentation.viewmodel.AuthorizationViewModel
import com.w4eret1ckrtb1tch.homework.ui.fragment.BaseFragment
import com.w4eret1ckrtb1tch.homework.ui.utils.showMessage
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
            userLogin.editText?.doBeforeTextChanged { _, _, _, _ -> userLogin.error = null }
            userPassword.editText?.doBeforeTextChanged { _, _, _, _ -> userPassword.error = null }
        }

        viewModel.getAuthToken.observe(viewLifecycleOwner) { resolveUserAuthorization(it) }
        viewModel.getIncorrectInputField.observe(viewLifecycleOwner) { validateInputField(it) }
    }

    private fun signInUser() {
        val name = binding.userLogin.editText?.text.toString()
        val password = binding.userPassword.editText?.text.toString()
        viewModel.signInUser(name, password)
    }

    private fun validateInputField(inputFieldError: InputFieldError) {
        when (inputFieldError) {
            InputFieldError.USER_NAME_EMPTY -> {
                binding.userLogin.error = getString(R.string.user_name_empty)
            }
            InputFieldError.USER_PASS_EMPTY -> {
                binding.userPassword.error = getString(R.string.user_password_empty)
            }
            else -> {
                showMessage(getString(R.string.unknown_error), binding.root, null)
            }

        }
    }

    private fun resolveUserAuthorization(result: Result<Unit>) {
        ResolveResultHelper.resolveResult(result,
            success = { openAuthUserToLoanList();loading(false) },
            failure = {
                showMessage(result.toString(), binding.root, null)
                loading(false)
            },
            loading = { loading(true) })
    }

    private fun loading(load: Boolean) {
        binding.loading.visibility = if (load) View.VISIBLE else View.GONE
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
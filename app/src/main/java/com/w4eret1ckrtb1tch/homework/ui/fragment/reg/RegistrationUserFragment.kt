package com.w4eret1ckrtb1tch.homework.ui.fragment.reg

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.widget.doBeforeTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.databinding.FragmentUserRegistrationBinding
import com.w4eret1ckrtb1tch.homework.domain.entity.UserEntity
import com.w4eret1ckrtb1tch.homework.presentation.model.InputFieldError
import com.w4eret1ckrtb1tch.homework.presentation.model.Result
import com.w4eret1ckrtb1tch.homework.presentation.viewmodel.RegistrationUserViewModel
import com.w4eret1ckrtb1tch.homework.ui.activity.MainActivity
import com.w4eret1ckrtb1tch.homework.ui.fragment.BaseFragment
import com.w4eret1ckrtb1tch.homework.presentation.utils.ResolveResultHelper
import com.w4eret1ckrtb1tch.homework.ui.utils.showMessage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationUserFragment : BaseFragment<FragmentUserRegistrationBinding>(
    FragmentUserRegistrationBinding::inflate,
    R.layout.fragment_user_registration
) {

    private val viewModel by viewModels<RegistrationUserViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            register.setOnClickListener { registerUser() }
            userLogin.editText?.doBeforeTextChanged { _, _, _, _ -> userLogin.error = null }
            userPassword.editText?.doBeforeTextChanged { _, _, _, _ -> userPassword.error = null }
        }
        viewModel.getUser.observe(viewLifecycleOwner) { resolveUserRegistration(it) }
        viewModel.getIncorrectInputField.observe(viewLifecycleOwner) { validateInputField(it) }
    }

    private fun registerUser() {
        val name = binding.userLogin.editText?.text.toString()
        val password = binding.userPassword.editText?.text.toString()
        viewModel.registerUser(name, password)
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
                (requireActivity() as MainActivity).showMessage(
                    getString(R.string.unknown_error),
                    binding.root,
                    null
                )
            }
        }
    }

    private fun resolveUserRegistration(result: Result<UserEntity>) {
        ResolveResultHelper.resolveResult(result,
            success = { openRegistrationUserToAuthUser(it);loading(false) },
            failure = {
                requireActivity().showMessage(
                    result.toString(),
                    binding.root,
                    null
                )
                loading(false)
            },
            loading = { loading(true) })
    }

    private fun loading(load: Boolean) {
        binding.loading.visibility = if (load) View.VISIBLE else View.GONE
    }

    private fun openRegistrationUserToAuthUser(user: UserEntity?) {
        Log.d("TAG", "openRegistrationUserToAuthUser: $user")
        val action = RegistrationUserFragmentDirections.actionRegUserToAuthUser(user)
        findNavController().navigate(action)
    }
}
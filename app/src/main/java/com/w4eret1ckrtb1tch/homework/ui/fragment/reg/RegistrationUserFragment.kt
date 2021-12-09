package com.w4eret1ckrtb1tch.homework.ui.fragment.reg

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.databinding.FragmentUserRegistrationBinding
import com.w4eret1ckrtb1tch.homework.domain.entity.Result
import com.w4eret1ckrtb1tch.homework.domain.entity.UserEntity
import com.w4eret1ckrtb1tch.homework.presentation.RegistrationUserViewModel
import com.w4eret1ckrtb1tch.homework.ui.activity.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegistrationUserFragment : Fragment(R.layout.fragment_user_registration) {

    private var binding: FragmentUserRegistrationBinding? = null
    private val viewModel by viewModels<RegistrationUserViewModel>()

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
            register.setOnClickListener { registerUser() }
        }
        viewModel.getUser.observe(viewLifecycleOwner) { resolveUserRegistration(it) }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun registerUser() {
        binding?.apply {
            val name = userLogin.editText?.text.toString()
            val password = userPassword.editText?.text.toString()
            viewModel.registerUser(name, password)
        }
    }

    private fun resolveUserRegistration(result: Result<UserEntity>) {
        when (result) {
            is Result.Success -> {
                openRegistrationUserToAuthUser(result.value)
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

    private fun openRegistrationUserToAuthUser(user: UserEntity?) {
        Log.d("TAG", "openRegistrationUserToAuthUser: $user")
        val action = RegistrationUserFragmentDirections.actionRegUserToAuthUser(user)
        findNavController().navigate(action)
    }
}
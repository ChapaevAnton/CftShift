package com.w4eret1ckrtb1tch.homework.ui.fragment.newloan

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.databinding.DialogSendBinding
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanEntity
import com.w4eret1ckrtb1tch.homework.presentation.viewmodel.SendDialogViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SendDialogFragment : DialogFragment(R.layout.dialog_send) {

    private var binding: DialogSendBinding? = null
    private val viewModel by viewModels<SendDialogViewModel>()
    private val args: SendDialogFragmentArgs by navArgs()
    private var loan: LoanEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loan = args.loanEntity
        loan?.let { viewModel.setLoan(it) }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogSendBinding.inflate(LayoutInflater.from(requireContext()))
        return AlertDialog.Builder(requireActivity())
            .setView(binding?.root)
            .setTitle(R.string.title_send_loan_dialog)
            .setPositiveButton(R.string.ok) { _, _ -> openSendLoanToLoanList() }
            .create().apply { setCanceledOnTouchOutside(false) }
    }

    override fun onCancel(dialog: DialogInterface) {
        openSendLoanToLoanList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLoan.observe(viewLifecycleOwner) { setLoanInfo(it) }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun setLoanInfo(loan: LoanEntity) {
        binding?.apply {
            state.text = loan.state.name
            idLoan.text = loan.id.toString()
            date.text = loan.date.toString()
            firstName.text = loan.firstName
            lastName.text = loan.lastName
            phoneNumber.text = loan.phoneNumber
            period.text = loan.period.toString()
            percent.text = loan.percent.toString()
            loan.amount.toString().also { amount.text = it }
        }
    }

    private fun openSendLoanToLoanList() {
        val action = SendDialogFragmentDirections.actionSendLoanToLoanList()
        findNavController().navigate(action)
    }
}
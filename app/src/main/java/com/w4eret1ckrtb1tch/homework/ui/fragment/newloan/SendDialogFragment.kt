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
            state.text = getString(R.string.state_data, loan.state.name)
            idLoan.text = getString(R.string.id_loan_data, loan.id)
            date.text = getString(R.string.date_data, loan.date)
            firstName.text = getString(R.string.first_name_data, loan.firstName)
            lastName.text = getString(R.string.last_name_data, loan.lastName)
            phoneNumber.text = getString(R.string.phone_number_data, loan.phoneNumber)
            period.text = getString(R.string.period_data, loan.period)
            percent.text = getString(R.string.percent_data, loan.percent)
            amount.text = getString(R.string.amount_data, loan.amount)
        }
    }

    private fun openSendLoanToLoanList() {
        val action = SendDialogFragmentDirections.actionSendLoanToLoanList()
        findNavController().navigate(action)
    }
}
package com.w4eret1ckrtb1tch.homework.ui.fragment.newloan

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.databinding.DialogSendLoanBinding
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanEntity

class SendLoanDialogFragment : DialogFragment(R.layout.dialog_send_loan) {

    private var binding: DialogSendLoanBinding? = null
    private val args: SendLoanDialogFragmentArgs by navArgs()
    private var loan: LoanEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loan = args.loanEntity
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogSendLoanBinding.inflate(LayoutInflater.from(requireContext()))
        return AlertDialog.Builder(requireActivity())
            .setView(binding?.root)
            .create()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loan?.let { setLoanData(it) }
    }


    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun setLoanData(loan: LoanEntity) {
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
}
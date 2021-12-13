package com.w4eret1ckrtb1tch.homework.ui.fragment.loanslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.w4eret1ckrtb1tch.homework.databinding.ItemLoansListBinding
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanEntity

class LoansAdapter(
    private val clickLoan: (id: Long?) -> Unit
) : RecyclerView.Adapter<LoansAdapter.LoansHolder>() {

    var loans: List<LoanEntity> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoansHolder =
        LoansHolder.create(parent, clickLoan)

    override fun onBindViewHolder(holder: LoansHolder, position: Int) =
        holder.bind(loans[position])

    override fun getItemCount(): Int = loans.size

    class LoansHolder private constructor(
        private val binding: ItemLoansListBinding,
        private val clickLoan: (id: Long?) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {

        companion object {
            fun create(
                parent: ViewGroup,
                clickLoan: (id: Long?) -> Unit
            ): LoansHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemLoansListBinding.inflate(inflater, parent, false)
                return LoansHolder(binding, clickLoan)
            }
        }

        fun bind(loan: LoanEntity?) {
            loan?.let {
                with(binding) {
                    id.text = loan.id.toString()
                    date.text = loan.date.toString()
                    loan.amount.toString().also { amount.text = it }
                    state.text = loan.state.name
                    root.setOnClickListener { clickLoan(loan.id) }
                }
            }
        }
    }
}
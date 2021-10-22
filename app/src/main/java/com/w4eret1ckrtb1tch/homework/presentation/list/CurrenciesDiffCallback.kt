package com.w4eret1ckrtb1tch.homework.presentation.list

import androidx.recyclerview.widget.DiffUtil
import com.w4eret1ckrtb1tch.homework.domain.model.Currency

object CurrenciesDiffCallback : DiffUtil.ItemCallback<Currency>() {
    override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem == newItem
    }

}
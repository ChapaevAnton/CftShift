package com.w4eret1ckrtb1tch.homework.presentation.list

import com.w4eret1ckrtb1tch.homework.domain.model.Currency

fun interface OnItemClickListener {

    fun onItemClick(currency: Currency)
}
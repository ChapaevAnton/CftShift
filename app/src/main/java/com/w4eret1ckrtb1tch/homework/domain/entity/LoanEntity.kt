package com.w4eret1ckrtb1tch.homework.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal
import java.util.*

@Parcelize
data class LoanEntity(
    val amount: BigDecimal,
    val date: Date,
    val firstName: String,
    val id: Long,
    val lastName: String,
    val percent: Double,
    val period: Int,
    val phoneNumber: String,
    val state: LoanState
) : Parcelable

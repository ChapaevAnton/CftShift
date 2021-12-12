package com.w4eret1ckrtb1tch.homework.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class LoanRequest(
    val amount: BigDecimal,
    val firstName: String,
    val lastName: String,
    val percent: Double,
    val period: Int,
    val phoneNumber: String
) : Parcelable
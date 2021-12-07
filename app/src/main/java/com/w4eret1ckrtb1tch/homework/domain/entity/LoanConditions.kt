package com.w4eret1ckrtb1tch.homework.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class LoanConditions(
    val maxAmount: BigDecimal,
    val percent: Double,
    val period: Int
) : Parcelable
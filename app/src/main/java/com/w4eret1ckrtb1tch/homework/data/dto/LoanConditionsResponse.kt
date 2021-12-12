package com.w4eret1ckrtb1tch.homework.data.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Parcelize
data class LoanConditionsResponse(
    @field:SerializedName("maxAmount") val maxAmount: BigDecimal,
    @field:SerializedName("percent") val percent: Double,
    @field:SerializedName("period") val period: Int
) : Parcelable

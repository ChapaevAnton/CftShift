package com.w4eret1ckrtb1tch.homework.data.dto

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal
import java.math.BigInteger

data class CurrencyResponse(
    @field:SerializedName("CharCode")
    val charCode: String,

    @field:SerializedName("ID")
    val id: String,

    @field:SerializedName("Name")
    val name: String,

    @field:SerializedName("Nominal")
    val nominal: BigInteger,

    @field:SerializedName("NumCode")
    val numCode: String,

    @field:SerializedName("Previous")
    val previous: BigDecimal,

    @field:SerializedName("Value")
    val value: BigDecimal
)
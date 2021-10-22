package com.w4eret1ckrtb1tch.homework.domain.model

import androidx.annotation.Keep
import java.io.Serializable
import java.math.BigDecimal
import java.math.BigInteger

@Keep
data class Currency(
    val charCode: String,
    val id: String,
    val name: String,
    val nominal: BigInteger,
    val numCode: String,
    val previous: BigDecimal,
    var value: BigDecimal
) : Serializable

package com.w4eret1ckrtb1tch.homework.kaspresso.config

import java.math.BigDecimal

fun BigDecimal.toRate() =
    String.format("( %1$.4f ₽ )", this)

fun BigDecimal.toValue() =
    String.format("%1$.4f ₽", this)

fun BigDecimal.toAmountCurrency() =
    String.format("%1$.4f", this)

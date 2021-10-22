package com.w4eret1ckrtb1tch.homework.kaspresso.data

import androidx.annotation.DrawableRes
import com.w4eret1ckrtb1tch.homework.R
import com.w4eret1ckrtb1tch.homework.kaspresso.config.ConfigTest.INPUT_CASH
import java.math.BigDecimal

data class Transaction(
    val charCode: String,
    val name: String,
    val value: BigDecimal,
    val previous: BigDecimal,
    val rate: BigDecimal = value.minus(previous),
    @DrawableRes
    val arrowId: Int,
    val amountCurrency: BigDecimal = INPUT_CASH.div(value)
)

object TransactionData {
    val transaction = arrayOf(
        Transaction(
            charCode = "AUD",
            name = "Австралийский доллар",
            value = BigDecimal(53.1292),
            previous = BigDecimal(53.249),
            arrowId = R.drawable.ic_down,
        ),
        Transaction(
            charCode = "AMD",
            name = "Армянских драмов",
            value = BigDecimal(14.864),
            previous = BigDecimal(14.8807),
            arrowId = R.drawable.ic_down

        ),
        Transaction(
            charCode = "GBP",
            name = "Фунт стерлингов Соединенного королевства",
            value = BigDecimal(97.9668),
            previous = BigDecimal(97.95),
            arrowId = R.drawable.ic_up
        ),
        Transaction(
            charCode = "AZN",
            name = "Азербайджанский манат",
            value = BigDecimal(41.7836),
            previous = BigDecimal(41.822),
            arrowId = R.drawable.ic_down
        ),
        Transaction(
            charCode = "BYN",
            name = "Белорусский рубль",
            value = BigDecimal(29.1685),
            previous = BigDecimal(29.133),
            arrowId = R.drawable.ic_up
        )
    )
}
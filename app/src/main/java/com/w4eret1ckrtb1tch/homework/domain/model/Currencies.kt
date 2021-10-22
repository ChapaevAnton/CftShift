package com.w4eret1ckrtb1tch.homework.domain.model


import androidx.annotation.Keep
import java.io.Serializable
import java.util.*
import kotlin.collections.HashMap

@Keep
data class Currencies(
    val date: Date,
    val previousDate: String,
    val previousURL: String,
    val timeStamp: Date,
    val currency: HashMap<String, Currency>
) : Serializable
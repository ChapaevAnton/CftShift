package com.w4eret1ckrtb1tch.homework.data.dto

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.HashMap

data class CurrenciesResponse(
    @field:SerializedName("Date")
    val date: Date,

    @field:SerializedName("PreviousDate")
    val previousDate: String,

    @field:SerializedName("PreviousURL")
    val previousURL: String,

    @field:SerializedName("Timestamp")
    val timeStamp: Date,

    @field:SerializedName("Valute")
    val currency: HashMap<String, CurrencyResponse> = hashMapOf()
)
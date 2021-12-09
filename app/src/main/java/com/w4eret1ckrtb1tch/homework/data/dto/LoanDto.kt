package com.w4eret1ckrtb1tch.homework.data.dto

import com.google.gson.annotations.SerializedName

data class LoanDto(
    @field:SerializedName("amount") val amount: Double,
    @field:SerializedName("date") val date: String,
    @field:SerializedName("firstName") val firstName: String,
    @field:SerializedName("id") val id: Long,
    @field:SerializedName("lastName") val lastName: String,
    @field:SerializedName("percent") val percent: Double,
    @field:SerializedName("period") val period: Int,
    @field:SerializedName("phoneNumber") val phoneNumber: String,
    @field:SerializedName("state") val state: String
)
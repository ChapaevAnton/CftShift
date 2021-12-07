package com.w4eret1ckrtb1tch.homework.data.dto

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @field:SerializedName("name") val name: String,
    @field:SerializedName("role") val role: String
)
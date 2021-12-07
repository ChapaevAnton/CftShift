package com.w4eret1ckrtb1tch.homework.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserAuth(
    val name: String,
    val password: String
) : Parcelable
package com.w4eret1ckrtb1tch.homework.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ContactDto(
    val id: Long = 0,
    val name: String,
    val number: String
) : Parcelable
package com.w4eret1ckrtb1tch.homework.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class LoanState : Parcelable {
    APPROVED,
    REGISTERED,
    REJECTED
}
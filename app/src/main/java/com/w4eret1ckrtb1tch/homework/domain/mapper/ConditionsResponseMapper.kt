package com.w4eret1ckrtb1tch.homework.domain.mapper

import com.w4eret1ckrtb1tch.homework.data.dto.LoanConditionsResponse
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanConditions

interface ConditionsResponseMapper {

    fun mapResponse(loanConditionsResponse: LoanConditionsResponse): LoanConditions
}
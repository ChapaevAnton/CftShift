package com.w4eret1ckrtb1tch.homework.data.mapper

import com.w4eret1ckrtb1tch.homework.data.dto.LoanConditionsResponse
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanConditions
import com.w4eret1ckrtb1tch.homework.domain.mapper.ConditionsResponseMapper
import javax.inject.Inject

class ConditionsResponseMapperImpl @Inject constructor() : ConditionsResponseMapper {

    override fun mapResponse(loanConditionsResponse: LoanConditionsResponse): LoanConditions {
        return with(loanConditionsResponse) {
            LoanConditions(
                maxAmount = maxAmount,
                percent = percent,
                period = period
            )
        }
    }
}
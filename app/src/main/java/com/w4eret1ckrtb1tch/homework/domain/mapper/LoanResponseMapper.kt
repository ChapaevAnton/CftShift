package com.w4eret1ckrtb1tch.homework.domain.mapper

import com.w4eret1ckrtb1tch.homework.data.dto.LoansResponse
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanEntity

interface LoanResponseMapper {

    fun mapResponse(loansResponse: LoansResponse): List<LoanEntity>
}
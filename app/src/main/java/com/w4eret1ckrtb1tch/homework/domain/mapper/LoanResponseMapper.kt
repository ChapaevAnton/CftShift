package com.w4eret1ckrtb1tch.homework.domain.mapper

import com.w4eret1ckrtb1tch.homework.data.dto.LoanDataResponse
import com.w4eret1ckrtb1tch.homework.data.dto.LoansListResponse
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanEntity

interface LoanResponseMapper {

    fun mapResponse(loanDataResponse: LoanDataResponse): LoanEntity

    fun mapResponse(loansListResponse: LoansListResponse): List<LoanEntity>
}
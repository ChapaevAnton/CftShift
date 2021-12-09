package com.w4eret1ckrtb1tch.homework.data.mapper

import com.w4eret1ckrtb1tch.homework.data.dto.LoansResponse
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanEntity
import com.w4eret1ckrtb1tch.homework.domain.mapper.LoanResponseMapper
import javax.inject.Inject

class LoanResponseMapperImpl @Inject constructor() : LoanResponseMapper {

    override fun mapResponse(loansResponse: LoansResponse): List<LoanEntity> {
        TODO("Not yet implemented")
    }
}
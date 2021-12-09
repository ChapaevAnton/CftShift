package com.w4eret1ckrtb1tch.homework.data.mapper

import com.w4eret1ckrtb1tch.homework.data.datasource.FocusStartApi
import com.w4eret1ckrtb1tch.homework.data.dto.LoansResponse
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanEntity
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanState
import com.w4eret1ckrtb1tch.homework.domain.mapper.LoanResponseMapper
import java.math.BigDecimal
import javax.inject.Inject

class LoanResponseMapperImpl @Inject constructor() : LoanResponseMapper {

    override fun mapResponse(loansResponse: LoansResponse): List<LoanEntity> {
        return loansResponse.map {
            LoanEntity(
                amount = BigDecimal(it.amount),
                date = it.date,
                firstName = it.firstName,
                id = it.id,
                lastName = it.lastName,
                percent = it.percent,
                period = it.period,
                phoneNumber = it.phoneNumber,
                state = getState(it.state)
            )
        }
    }

    private fun getState(state: String): LoanState {
        return when (state) {
            FocusStartApi.LOAN_STATE_APPROVED -> LoanState.APPROVED
            FocusStartApi.LOAN_STATE_REGISTERED -> LoanState.REGISTERED
            FocusStartApi.LOAN_STATE_REJECTED -> LoanState.REJECTED
            else -> throw IllegalArgumentException("State missing")
        }
    }
}
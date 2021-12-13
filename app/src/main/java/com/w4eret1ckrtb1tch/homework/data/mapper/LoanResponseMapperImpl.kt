package com.w4eret1ckrtb1tch.homework.data.mapper

import com.w4eret1ckrtb1tch.homework.data.datasource.network.FocusStartApi
import com.w4eret1ckrtb1tch.homework.data.dto.LoanDataResponse
import com.w4eret1ckrtb1tch.homework.data.dto.LoansListResponse
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanEntity
import com.w4eret1ckrtb1tch.homework.domain.entity.LoanState
import com.w4eret1ckrtb1tch.homework.domain.mapper.LoanResponseMapper
import java.math.BigDecimal
import javax.inject.Inject

class LoanResponseMapperImpl @Inject constructor() : LoanResponseMapper {

    override fun mapResponse(loanDataResponse: LoanDataResponse): LoanEntity {
        return with(loanDataResponse) {
            LoanEntity(
                amount = BigDecimal(amount),
                date = date,
                firstName = firstName,
                id = id,
                lastName = lastName,
                percent = percent,
                period = period,
                phoneNumber = phoneNumber,
                state = getState(state)
            )
        }
    }

    override fun mapResponse(loansListResponse: LoansListResponse): List<LoanEntity> {
        return loansListResponse.map { mapResponse(it) }
    }

    private fun getState(state: String): LoanState {
        return when (state) {
            FocusStartApi.LOAN_STATE_APPROVED -> LoanState.APPROVED
            FocusStartApi.LOAN_STATE_REGISTERED -> LoanState.REGISTERED
            FocusStartApi.LOAN_STATE_REJECTED -> LoanState.REJECTED
            else -> LoanState.UNKNOWN
        }
    }
}
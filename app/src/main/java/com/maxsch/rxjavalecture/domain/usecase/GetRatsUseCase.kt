package com.maxsch.rxjavalecture.domain.usecase

import com.maxsch.rxjavalecture.domain.entities.Rat
import com.maxsch.rxjavalecture.domain.repository.RatsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetRatsUseCase(private val ratsRepository: RatsRepository) {

    suspend operator fun invoke(): List<Rat> {
        return withContext(Dispatchers.IO) {
            ratsRepository.getRats()
        }
    }
}
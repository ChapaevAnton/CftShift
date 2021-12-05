package com.maxsch.rxjavalecture.domain.usecase

import com.maxsch.rxjavalecture.domain.entities.Cat
import com.maxsch.rxjavalecture.domain.repository.CatsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCatsUseCase(private val catsRepository: CatsRepository) {

    suspend operator fun invoke(): List<Cat> {
        return withContext(Dispatchers.IO) {
            catsRepository.getCats()
        }
    }
}
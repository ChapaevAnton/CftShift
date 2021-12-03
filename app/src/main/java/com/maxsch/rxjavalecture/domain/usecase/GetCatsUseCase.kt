package com.maxsch.rxjavalecture.domain.usecase

import com.maxsch.rxjavalecture.domain.entities.Cat
import com.maxsch.rxjavalecture.domain.repository.CatsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCatsUseCase @Inject constructor(private val catsRepository: CatsRepository) {

    suspend operator fun invoke(): List<Cat> {
        return withContext(Dispatchers.IO) {
            catsRepository.getCats()
        }
    }
}
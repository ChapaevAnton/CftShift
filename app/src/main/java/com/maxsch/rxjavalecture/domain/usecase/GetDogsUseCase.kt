package com.maxsch.rxjavalecture.domain.usecase

import com.maxsch.rxjavalecture.domain.entities.Dog
import com.maxsch.rxjavalecture.domain.repository.DogsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetDogsUseCase(private val dogsRepository: DogsRepository) {

    suspend operator fun invoke(): List<Dog> {
        return withContext(Dispatchers.IO) {
            dogsRepository.getDogs()
        }
    }
}
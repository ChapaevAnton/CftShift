package com.maxsch.rxjavalecture.domain.usecase

import com.maxsch.rxjavalecture.domain.entities.Animal
import com.maxsch.rxjavalecture.domain.repository.PriceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPriceUseCase(private val priceRepository: PriceRepository) {

    suspend operator fun invoke(animal: Animal): Int {
        return withContext(Dispatchers.IO) {
            priceRepository.getPrice(animal)
        }
    }
}
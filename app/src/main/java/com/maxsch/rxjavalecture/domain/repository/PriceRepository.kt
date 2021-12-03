package com.maxsch.rxjavalecture.domain.repository

import com.maxsch.rxjavalecture.domain.entities.Animal

interface PriceRepository {
    suspend fun getPrice(animal: Animal): Int
}
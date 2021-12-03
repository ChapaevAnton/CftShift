package com.maxsch.rxjavalecture.domain.repository

import com.maxsch.rxjavalecture.domain.entities.Dog

interface DogsRepository {
    suspend fun getDogs(): List<Dog>
}
package com.maxsch.rxjavalecture.domain.repository

import com.maxsch.rxjavalecture.domain.entities.Cat

interface CatsRepository {
    suspend fun getCats():List<Cat>
}
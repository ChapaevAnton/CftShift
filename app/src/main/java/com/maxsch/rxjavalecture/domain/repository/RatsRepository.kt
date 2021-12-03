package com.maxsch.rxjavalecture.domain.repository

import com.maxsch.rxjavalecture.domain.entities.Rat

interface RatsRepository {
    suspend fun getRats(): List<Rat>
}
package com.maxsch.rxjavalecture.data.datasource

import com.maxsch.rxjavalecture.domain.entities.Animal
import com.maxsch.rxjavalecture.domain.entities.Cat
import com.maxsch.rxjavalecture.domain.entities.Dog
import com.maxsch.rxjavalecture.domain.entities.Rat
import javax.inject.Inject

interface PriceApi {

    fun getPrice(animal: Animal): Int
}

class PriceApiImpl @Inject constructor() : PriceApi {

    override fun getPrice(animal: Animal): Int =
        when (animal) {
            is Cat -> 1
            is Dog -> 2
            is Rat -> 3
            else -> throw IllegalArgumentException("Unknown animal")
        }
}
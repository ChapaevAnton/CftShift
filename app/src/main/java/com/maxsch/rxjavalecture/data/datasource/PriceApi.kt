package com.maxsch.rxjavalecture.data.datasource

import com.maxsch.rxjavalecture.domain.entities.Animal
import com.maxsch.rxjavalecture.domain.entities.Cat
import com.maxsch.rxjavalecture.domain.entities.Dog
import com.maxsch.rxjavalecture.domain.entities.Rat

interface PriceApi {

    fun getPrice(animal: Animal): Int
}

class PriceApiDebugImpl : PriceApi {

    override fun getPrice(animal: Animal): Int =
        when (animal) {
            is Cat -> 1
            is Dog -> 2
            is Rat -> 3
            else -> throw IllegalArgumentException("Unknown animal")
        }
}

class PriceApiProdImpl : PriceApi {

    override fun getPrice(animal: Animal): Int =
        when (animal) {
            is Cat -> 4
            is Dog -> 5
            is Rat -> 6
            else -> throw IllegalArgumentException("Unknown animal")
        }
}
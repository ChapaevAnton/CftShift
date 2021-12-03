package com.maxsch.rxjavalecture.data.datasource

import com.maxsch.rxjavalecture.domain.entities.Cat
import javax.inject.Inject


interface CatsApi {

    fun getCats(): List<Cat>
}

class CatsApiImpl @Inject constructor() : CatsApi {

    override fun getCats(): List<Cat> = listOf(Cat("John", "10"))
}
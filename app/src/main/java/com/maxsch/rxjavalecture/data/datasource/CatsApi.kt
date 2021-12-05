package com.maxsch.rxjavalecture.data.datasource

import com.maxsch.rxjavalecture.domain.entities.Cat


interface CatsApi {

    fun getCats(): List<Cat>
}

class CatsApiImpl : CatsApi {

    override fun getCats(): List<Cat> = listOf(Cat("John", "10"))
}
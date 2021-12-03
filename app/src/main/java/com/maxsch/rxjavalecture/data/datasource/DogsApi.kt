package com.maxsch.rxjavalecture.data.datasource

import com.maxsch.rxjavalecture.domain.entities.Dog
import javax.inject.Inject


interface DogsApi {

    fun getDogs(): List<Dog>
}

class DogsApiImpl @Inject constructor() : DogsApi {

    override fun getDogs(): List<Dog> = listOf(Dog("Michel", "4"))
}
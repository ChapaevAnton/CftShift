package com.maxsch.rxjavalecture.data.datasource

import com.maxsch.rxjavalecture.domain.entities.Dog


interface DogsApi {

    fun getDogs(): List<Dog>
}

class DogsApiImpl : DogsApi {

    override fun getDogs(): List<Dog> = listOf(Dog("Michel", "4"))
}
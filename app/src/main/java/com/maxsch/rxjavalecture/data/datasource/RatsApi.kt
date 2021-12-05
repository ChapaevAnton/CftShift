package com.maxsch.rxjavalecture.data.datasource

import com.maxsch.rxjavalecture.domain.entities.Rat


interface RatsApi {

    fun getRats(): List<Rat>
}

class RatsApiImpl : RatsApi {

    override fun getRats(): List<Rat> = listOf(Rat("Christie", "1"))
}
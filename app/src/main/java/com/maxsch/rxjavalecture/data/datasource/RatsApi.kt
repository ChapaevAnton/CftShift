package com.maxsch.rxjavalecture.data.datasource

import com.maxsch.rxjavalecture.domain.entities.Rat
import javax.inject.Inject


interface RatsApi {

    fun getRats(): List<Rat>
}

class RatsApiImpl @Inject constructor() : RatsApi {

    override fun getRats(): List<Rat> = listOf(Rat("Christie", "1"))
}
package com.maxsch.rxjavalecture.api

import com.maxsch.rxjavalecture.entities.Rat

interface RatsApi {

	fun getRats(listener: ResultListener<List<Rat>>)
}

class RatsApiImpl : RatsApi {

	override fun getRats(listener: ResultListener<List<Rat>>) {
		listener.onSuccess(
			listOf(Rat("Christie", "1"))
		)
	}
}
package com.maxsch.rxjavalecture.api

import com.maxsch.rxjavalecture.entities.Cat

interface CatsApi {

	fun getCats(listener: ResultListener<List<Cat>>)
}

class CatsApiImpl : CatsApi {

	override fun getCats(listener: ResultListener<List<Cat>>) {
		listener.onSuccess(
			listOf(Cat("John", "10"))
		)
	}
}
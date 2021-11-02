package com.maxsch.rxjavalecture.api

import com.maxsch.rxjavalecture.entities.Dog

interface DogsApi {

	fun getDogs(listener: ResultListener<List<Dog>>)
}

class DogsApiImpl : DogsApi {

	override fun getDogs(listener: ResultListener<List<Dog>>) {
		listener.onSuccess(
			listOf(Dog("Michel", "4"))
		)
	}
}
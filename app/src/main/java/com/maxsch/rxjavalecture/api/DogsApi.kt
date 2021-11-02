package com.maxsch.rxjavalecture.api

import com.maxsch.rxjavalecture.entities.Dog
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

interface DogsApi {

	fun getDogs(): Single<List<Dog>>
}

class DogsApiImpl : DogsApi {

	override fun getDogs(): Single<List<Dog>> =
		Single.create<List<Dog>> { emitter ->
			emitter.onSuccess(
				listOf(Dog("Michel", "4"))
			)
		}.subscribeOn(Schedulers.io())
}
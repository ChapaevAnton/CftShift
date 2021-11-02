package com.maxsch.rxjavalecture.api

import com.maxsch.rxjavalecture.entities.Cat
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

interface CatsApi {

	fun getCats(): Single<List<Cat>>
}

class CatsApiImpl : CatsApi {

	override fun getCats(): Single<List<Cat>> =
		Single.create<List<Cat>> { emitter ->
			emitter.onSuccess(
				listOf(Cat("John", "10"))
			)
		}.subscribeOn(Schedulers.io())
}
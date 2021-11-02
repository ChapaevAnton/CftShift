package com.maxsch.rxjavalecture.api

import com.maxsch.rxjavalecture.entities.Cat
import io.reactivex.Single

interface CatsApi {

	fun getCats(): Single<List<Cat>>
}

class CatsApiImpl : CatsApi {

	override fun getCats(): Single<List<Cat>> =
		Single.create { emitter ->
			emitter.onSuccess(
				listOf(Cat("John", "10"))
			)
		}
}
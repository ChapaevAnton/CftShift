package com.maxsch.rxjavalecture.api

import com.maxsch.rxjavalecture.entities.Rat
import io.reactivex.Single

interface RatsApi {

	fun getRats(): Single<List<Rat>>
}

class RatsApiImpl : RatsApi {

	override fun getRats(): Single<List<Rat>> =
		Single.create { emitter ->
			emitter.onSuccess(
				listOf(Rat("Christie", "1"))
			)
		}
}
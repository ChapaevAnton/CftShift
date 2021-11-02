package com.maxsch.rxjavalecture.api

import com.maxsch.rxjavalecture.entities.Animal
import com.maxsch.rxjavalecture.entities.Cat
import com.maxsch.rxjavalecture.entities.Dog
import com.maxsch.rxjavalecture.entities.Rat
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

interface PriceApi {

	fun getPrice(animal: Animal): Single<Int>
}

class PriceApiImpl : PriceApi {

	override fun getPrice(animal: Animal): Single<Int> =
		Single.create<Int> { emitter ->
			val value = when (animal) {
				is Cat -> 1
				is Dog -> 2
				is Rat -> 3
				else   -> throw IllegalArgumentException("Unknown animal")
			}
			emitter.onSuccess(value)
		}.subscribeOn(Schedulers.io())
}
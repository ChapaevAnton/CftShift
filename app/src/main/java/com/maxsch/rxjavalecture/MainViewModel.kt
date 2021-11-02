package com.maxsch.rxjavalecture

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maxsch.rxjavalecture.api.CatsApi
import com.maxsch.rxjavalecture.api.CatsApiImpl
import com.maxsch.rxjavalecture.api.DogsApi
import com.maxsch.rxjavalecture.api.DogsApiImpl
import com.maxsch.rxjavalecture.api.PriceApi
import com.maxsch.rxjavalecture.api.PriceApiImpl
import com.maxsch.rxjavalecture.api.RatsApi
import com.maxsch.rxjavalecture.api.RatsApiImpl
import com.maxsch.rxjavalecture.api.ResultListener
import com.maxsch.rxjavalecture.entities.Animal
import com.maxsch.rxjavalecture.entities.Cat
import com.maxsch.rxjavalecture.entities.Dog
import com.maxsch.rxjavalecture.entities.Rat

class MainViewModel(
	private val catsApi: CatsApi = CatsApiImpl(),
	private val dogsApi: DogsApi = DogsApiImpl(),
	private val ratsApi: RatsApi = RatsApiImpl(),
	private val priceApi: PriceApi = PriceApiImpl(),
) : ViewModel() {

	private var catsReady = false
	private var dogsReady = false
	private var ratsReady = false

	private val tmpAnimalCollector = mutableListOf<Animal>()

	private val _result = MutableLiveData<Map<Animal, Int>>()
	val result: LiveData<Map<Animal, Int>> = _result

	private val catsListener = object : ResultListener<List<Cat>> {
		override fun onSuccess(value: List<Cat>) {
			tmpAnimalCollector.addAll(value)
			catsReady = true
			onAnimalsReady()
		}

		override fun onError(error: Throwable) {
			handleError(error)
		}
	}

	private val dogsListener = object : ResultListener<List<Dog>> {
		override fun onSuccess(value: List<Dog>) {
			tmpAnimalCollector.addAll(value)
			dogsReady = true
			onAnimalsReady()
		}

		override fun onError(error: Throwable) {
			handleError(error)
		}
	}

	private val ratsListener = object : ResultListener<List<Rat>> {
		override fun onSuccess(value: List<Rat>) {
			tmpAnimalCollector.addAll(value)
			ratsReady = true
			onAnimalsReady()
		}

		override fun onError(error: Throwable) {
			handleError(error)
		}
	}

	fun getData() {
		tmpAnimalCollector.clear()

		catsApi.getCats(catsListener)
		dogsApi.getDogs(dogsListener)
		ratsApi.getRats(ratsListener)
	}

	private fun onAnimalsReady() {
		if (ratsReady && catsReady && dogsReady) {
			loadPrices(tmpAnimalCollector)
		}
	}

	private fun loadPrices(animals: List<Animal>) {
		val animalToPriceMap: MutableMap<Animal, Int> = mutableMapOf()

		for (animal in animals) {
			val listener = object : ResultListener<Int> {
				override fun onSuccess(value: Int) {
					animalToPriceMap[animal] = value
				}

				override fun onError(error: Throwable) {
					loadPrices(animals)
				}
			}

			priceApi.getPrice(animal, listener)
		}

		handleAnimalPrices(animalToPriceMap)
	}

	private fun handleAnimalPrices(animalToPriceMap: Map<Animal, Int>) {
		_result.value = animalToPriceMap
	}

	private fun handleError(error: Throwable) {
		Log.e("MainViewModel", "Failed to load animals and prices", error)
	}
}
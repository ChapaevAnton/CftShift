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
import com.maxsch.rxjavalecture.entities.Animal
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Singles
import io.reactivex.rxkotlin.toMap

class MainViewModel(
	private val catsApi: CatsApi = CatsApiImpl(),
	private val dogsApi: DogsApi = DogsApiImpl(),
	private val ratsApi: RatsApi = RatsApiImpl(),
	private val priceApi: PriceApi = PriceApiImpl(),
) : ViewModel() {

	private val _result = MutableLiveData<Map<Animal, Int>>()
	val result: LiveData<Map<Animal, Int>> = _result

	private var disposable: Disposable? = null

	fun getData() {
		disposable = Singles.zip(catsApi.getCats(), dogsApi.getDogs(), ratsApi.getRats())
			.map { (cats, dogs, rats) -> cats + dogs + rats }
			.flattenAsObservable { it }
			.flatMapSingle { animal -> priceApi.getPrice(animal).map { price -> animal to price } }
			.toMap()
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(::handleAnimalPrices, ::handleLoadingError)
	}

	private fun handleAnimalPrices(animalToPriceMap: Map<Animal, Int>) {
		_result.value = animalToPriceMap
	}

	private fun handleLoadingError(error: Throwable) {
		Log.e("MainViewModel", "Failed to load animals and prices", error)
	}

	override fun onCleared() {
		super.onCleared()
		disposable?.dispose()
	}
}
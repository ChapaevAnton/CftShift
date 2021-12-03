package com.maxsch.rxjavalecture.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxsch.rxjavalecture.domain.entities.Animal
import com.maxsch.rxjavalecture.domain.usecase.GetCatsUseCase
import com.maxsch.rxjavalecture.domain.usecase.GetPriceUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainViewModel @Inject constructor(
    private val getCatsUseCase: GetCatsUseCase,
    private val getPriceUseCase: GetPriceUseCase
) : ViewModel() {

    private val _result = MutableLiveData<Map<Animal, Int>>()
    val result: LiveData<Map<Animal, Int>> = _result

    private val exceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            handleLoadingError(throwable)
        }

    fun getResult() {
        viewModelScope.launch(exceptionHandler) {
            val animals = getAnimals()
            handleAnimalPrices(animals)
        }
    }

    private suspend fun getAnimals(): Map<Animal, Int> = (getCatsUseCase()).map { animal ->
        val price = getPriceUseCase(animal)
        return@map Pair(animal, price)
    }.toMap()

//        disposable = Singles.zip(catsApi.getCats(), dogsApi.getDogs(), ratsApi.getRats())
//            .map { (cats, dogs, rats) -> cats + dogs + rats }
//            .flattenAsObservable { it }
//            .flatMapSingle { animal -> priceApi.getPrice(animal).map { price -> animal to price } }
//            .toMap()
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(::handleAnimalPrices, ::handleLoadingError)


    private fun handleAnimalPrices(animalToPriceMap: Map<Animal, Int>) {
        _result.value = animalToPriceMap
    }

    private fun handleLoadingError(error: Throwable) {
        Log.e("MainViewModel", "Failed to load animals and prices", error)
    }
}
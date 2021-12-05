package com.maxsch.rxjavalecture.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maxsch.rxjavalecture.domain.entities.Animal
import com.maxsch.rxjavalecture.domain.usecase.GetCatsUseCase
import com.maxsch.rxjavalecture.domain.usecase.GetDogsUseCase
import com.maxsch.rxjavalecture.domain.usecase.GetPriceUseCase
import com.maxsch.rxjavalecture.domain.usecase.GetRatsUseCase
import kotlinx.coroutines.*


class MainViewModel(
    private val getCatsUseCase: GetCatsUseCase,
    private val getPriceUseCase: GetPriceUseCase,
    private val getDogsUseCase: GetDogsUseCase,
    private val getRatsUseCase: GetRatsUseCase
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

    private suspend fun getAnimals(): Map<Animal, Int> =
        supervisorScope {
            val cats = runCatching { getCatsUseCase() }.getOrDefault(emptyList())
            val dogs = runCatching { getDogsUseCase() }.getOrDefault(emptyList())
            val rats = runCatching { getRatsUseCase() }.getOrDefault(emptyList())

            return@supervisorScope listOf(cats, dogs, rats)
                .flatten()
                .map { animal ->
                    val price = getPriceUseCase(animal)
                    return@map Pair(animal, price)
                }.toMap()
        }


    private fun handleAnimalPrices(animalToPriceMap: Map<Animal, Int>) {
        _result.value = animalToPriceMap
    }

    private fun handleLoadingError(error: Throwable) {
        Log.e("MainViewModel", "Failed to load animals and prices", error)
    }
}
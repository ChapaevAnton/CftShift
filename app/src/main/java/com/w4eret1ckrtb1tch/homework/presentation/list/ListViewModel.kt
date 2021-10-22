package com.w4eret1ckrtb1tch.homework.presentation.list

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.w4eret1ckrtb1tch.homework.di.CurrentRepository
import com.w4eret1ckrtb1tch.homework.domain.model.Currency
import com.w4eret1ckrtb1tch.homework.domain.repository.CurrenciesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.DateFormat
import javax.inject.Inject

@HiltViewModel
class ListViewModel
@Inject constructor(
    @CurrentRepository
    private val repository: CurrenciesRepository
) : ViewModel() {

    private val currencies: MutableLiveData<List<Currency>> = MutableLiveData()
    private val date: MutableLiveData<String> = MutableLiveData()

    init {
        loadCurrencies()
    }

    fun getCurrencies(): LiveData<List<Currency>> = currencies
    fun getDate(): LiveData<String> = date

    @SuppressLint("CheckResult")
    fun loadCurrencies() {
        repository.getCurrencies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d("TAG", "loadCurrencies: load")
                    currencies.value = it.currency.values.toList()
                    date.value = DateFormat.getDateInstance().format(it.date)
                },
                { error ->
                    Log.d("TAG", error.toString())
                })
    }
}
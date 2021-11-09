package com.w4eret1ckrtb1tch.homework.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.w4eret1ckrtb1tch.homework.di.FragmentScope
import javax.inject.Inject
import javax.inject.Provider

@FragmentScope
class ViewModelFactory @Inject constructor(
    private val viewModelFactory: MutableMap<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModelFactory.getValue(modelClass as Class<ViewModel>).get() as T
    }
}
package com.w4eret1ckrtb1tch.homework.di

import androidx.recyclerview.widget.ListAdapter
import com.w4eret1ckrtb1tch.homework.domain.model.Currency
import com.w4eret1ckrtb1tch.homework.presentation.list.CurrenciesAdapter
import dagger.Binds
import dagger.Module
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
abstract class FragmentModule {

    @Binds
    @Reusable
    abstract fun bindCurrenciesAdapter(
        currenciesAdapter: CurrenciesAdapter
    ): ListAdapter<Currency, CurrenciesAdapter.CurrencyViewHolder>

}
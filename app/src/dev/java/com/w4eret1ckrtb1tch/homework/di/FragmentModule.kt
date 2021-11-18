package com.w4eret1ckrtb1tch.homework.di

import androidx.lifecycle.ViewModel
import com.w4eret1ckrtb1tch.homework.presentation.viewmodel.ListViewModel
import com.w4eret1ckrtb1tch.homework.presentation.fragment.added.AddedFragment
import com.w4eret1ckrtb1tch.homework.ui.fragment.list.ListFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module
interface FragmentModule {

    @ContributesAndroidInjector(modules = [ViewModelFactoryModule::class])
    fun listFragment(): ListFragment

    @ContributesAndroidInjector(modules = [ViewModelFactoryModule::class])
    fun addedFragment(): AddedFragment

    @Binds
    @[IntoMap ViewModelKey(ListViewModel::class)]
    fun bindsListViewModel(listViewModel: ListViewModel): ViewModel

}
package com.w4eret1ckrtb1tch.homework.di

import androidx.fragment.app.Fragment
import com.w4eret1ckrtb1tch.homework.presentation.utils.FragmentRouter
import com.w4eret1ckrtb1tch.homework.presentation.utils.FragmentRouterImpl
import com.w4eret1ckrtb1tch.homework.ui.activity.MainActivity
import com.w4eret1ckrtb1tch.homework.ui.fragment.added.AddedFragment
import com.w4eret1ckrtb1tch.homework.ui.fragment.list.ListFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface ActivityModule {

    @ContributesAndroidInjector(modules = [ViewModelFactoryModule::class, FragmentFactoryModule::class])
    fun mainActivity(): MainActivity


    @Binds
    fun bindsFragmentRouter(fragmentRouter: FragmentRouterImpl): FragmentRouter

    @Binds
    @[IntoMap FragmentKey(ListFragment::class)]
    fun bindsListFragment(listFragment: ListFragment): Fragment

    @Binds
    @[IntoMap FragmentKey(AddedFragment::class)]
    fun bindsAddedFragment(addedFragment: AddedFragment): Fragment
}
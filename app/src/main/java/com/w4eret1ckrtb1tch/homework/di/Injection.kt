package com.w4eret1ckrtb1tch.homework.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.w4eret1ckrtb1tch.homework.data.datasource.ContentResolverImpl
import com.w4eret1ckrtb1tch.homework.data.db.AppDataBase
import com.w4eret1ckrtb1tch.homework.data.repository.ContactRepositoryImpl
import com.w4eret1ckrtb1tch.homework.domain.datasource.ContentResolver
import com.w4eret1ckrtb1tch.homework.domain.repository.ContactRepository
import com.w4eret1ckrtb1tch.homework.presentation.viewmodel.list.ListViewModelFactory

object Injection {

    fun provideDataBase(context: Context): AppDataBase = AppDataBase.getDatabase(context)

    fun provideContentResolver(context: Context): ContentResolver = ContentResolverImpl(context)

    fun provideContactRepository(
        dataBase: AppDataBase,
        contentResolver: ContentResolver
    ): ContactRepository = ContactRepositoryImpl(dataBase, contentResolver)

    fun provideListViewModel(context: Context): ViewModelProvider.Factory {
        val dataBase = provideDataBase(context)
        val contentResolver = provideContentResolver(context)
        val contactRepository = provideContactRepository(dataBase, contentResolver)
        return ListViewModelFactory(context, contactRepository)
    }

}
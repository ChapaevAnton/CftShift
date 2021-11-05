package com.w4eret1ckrtb1tch.homework.di

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.w4eret1ckrtb1tch.homework.data.datasource.ContactDataSource
import com.w4eret1ckrtb1tch.homework.data.datasource.ContactDataSourceImpl
import com.w4eret1ckrtb1tch.homework.data.datasource.ContentDataSource
import com.w4eret1ckrtb1tch.homework.data.datasource.ContentDataSourceImpl
import com.w4eret1ckrtb1tch.homework.data.db.AppDataBase
import com.w4eret1ckrtb1tch.homework.data.repository.ContactRepositoryImpl
import com.w4eret1ckrtb1tch.homework.data.repository.ContentRepositoryImpl
import com.w4eret1ckrtb1tch.homework.domain.repository.ContactRepository
import com.w4eret1ckrtb1tch.homework.domain.repository.ContentRepository
import com.w4eret1ckrtb1tch.homework.domain.usecase.contacts.GetContactsUseCase
import com.w4eret1ckrtb1tch.homework.domain.usecase.contacts.RemoveContactUseCase
import com.w4eret1ckrtb1tch.homework.presentation.viewmodel.list.ListViewModelFactory

object Injection {

    fun provideDataBase(context: Context): AppDataBase = AppDataBase.getDatabase(context)

    fun provideContentDataSource(context: Context): ContentDataSource =
        ContentDataSourceImpl(context)

    fun provideContactDataSource(
        dataBase: AppDataBase
    ): ContactDataSource = ContactDataSourceImpl(dataBase)

    fun provideContentRepository(
        contentDataSource: ContentDataSource
    ): ContentRepository = ContentRepositoryImpl(contentDataSource)

    fun provideContactRepository(
        contactDataSource: ContactDataSource
    ): ContactRepository = ContactRepositoryImpl(contactDataSource)

    fun provideGetContactsUseCase(
        contentRepository: ContentRepository,
        contactRepository: ContactRepository
    ): GetContactsUseCase = GetContactsUseCase(contentRepository, contactRepository)

    fun provideRemoveContactUseCase(
        contactRepository: ContactRepository
    ): RemoveContactUseCase = RemoveContactUseCase(contactRepository)

    fun provideListViewModel(
        context: Context
    ): ViewModelProvider.Factory {
        val provideDataBase = provideDataBase(context)
        val contentDataSource = provideContentDataSource(context)
        val contactDataSource = provideContactDataSource(provideDataBase)
        val contentRepository = provideContentRepository(contentDataSource)
        val contactRepository = provideContactRepository(contactDataSource)
        val getContactsUseCase = provideGetContactsUseCase(contentRepository, contactRepository)
        val removeContactUseCase = provideRemoveContactUseCase(contactRepository)
        return ListViewModelFactory(context, getContactsUseCase, removeContactUseCase)
    }

}
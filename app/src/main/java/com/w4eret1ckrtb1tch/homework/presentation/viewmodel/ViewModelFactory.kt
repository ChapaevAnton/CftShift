package com.w4eret1ckrtb1tch.homework.presentation.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.w4eret1ckrtb1tch.homework.domain.usecase.contacts.GetContactsUseCase
import com.w4eret1ckrtb1tch.homework.domain.usecase.contacts.RemoveContactUseCase
import com.w4eret1ckrtb1tch.homework.presentation.viewmodel.list.ListViewModel

class ViewModelFactory private constructor(
    private val context: Context,
    private val getContactsUseCase: GetContactsUseCase,
    private val removeContactUseCase: RemoveContactUseCase,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            try {
                modelClass.getConstructor(
                    SavedStateHandle::class.java,
                    Context::class.java,
                    GetContactsUseCase::class.java,
                    RemoveContactUseCase::class.java
                ).newInstance(handle, context, getContactsUseCase, removeContactUseCase) as T
            } catch (error: Exception) {
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        } else super.create(modelClass)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var newInstance: ViewModelFactory? = null
        fun getInstance(
            owner: SavedStateRegistryOwner,
            context: Context,
            getContactsUseCase: GetContactsUseCase,
            removeContactUseCase: RemoveContactUseCase
        ): ViewModelFactory {
            if (newInstance == null) {
                newInstance =
                    ViewModelFactory(context, getContactsUseCase, removeContactUseCase, owner)
            }
            return newInstance as ViewModelFactory
        }
    }
}



package com.w4eret1ckrtb1tch.homework.presentation.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.w4eret1ckrtb1tch.homework.domain.usecase.contacts.GetContactsUseCase
import com.w4eret1ckrtb1tch.homework.domain.usecase.contacts.RemoveContactUseCase
import com.w4eret1ckrtb1tch.homework.presentation.viewmodel.list.ListViewModel

class ViewModelFactory private constructor(
    private val context: Context,
    private val getContactsUseCase: GetContactsUseCase,
    private val removeContactUseCase: RemoveContactUseCase
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            try {
                modelClass.getConstructor(
                    Context::class.java,
                    GetContactsUseCase::class.java,
                    RemoveContactUseCase::class.java
                ).newInstance(context, getContactsUseCase, removeContactUseCase) as T
            } catch (error: Exception) {
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        } else super.create(modelClass)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var newInstance: ViewModelFactory? = null
        fun getInstance(
            context: Context,
            getContactsUseCase: GetContactsUseCase,
            removeContactUseCase: RemoveContactUseCase
        ): ViewModelFactory {
            if (newInstance == null) {
                newInstance =
                    ViewModelFactory(context, getContactsUseCase, removeContactUseCase)
            }
            return newInstance as ViewModelFactory
        }
    }
}



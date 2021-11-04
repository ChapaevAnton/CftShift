package com.w4eret1ckrtb1tch.homework.presentation.viewmodel.list

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.w4eret1ckrtb1tch.homework.domain.repository.ContactRepository

class ListViewModelFactory(
    private val context: Context,
    private val contactRepository: ContactRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return ListViewModel(context, contactRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
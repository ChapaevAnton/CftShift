package com.w4eret1ckrtb1tch.homework.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.w4eret1ckrtb1tch.homework.domain.entity.ListItem
import com.w4eret1ckrtb1tch.homework.domain.usecase.GetItemsUseCase
import javax.inject.Inject

class ListViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase
) : ViewModel() {

    private val items: MutableLiveData<List<ListItem>> = MutableLiveData()
    val getItems: LiveData<List<ListItem>> = items

    init {
        loadItems()
    }

    private fun loadItems() {
        items.value = getItemsUseCase()
    }
}
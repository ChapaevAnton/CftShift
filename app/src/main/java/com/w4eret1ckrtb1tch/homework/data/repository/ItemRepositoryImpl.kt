package com.w4eret1ckrtb1tch.homework.data.repository

import com.w4eret1ckrtb1tch.homework.data.datasource.ItemDataSource
import com.w4eret1ckrtb1tch.homework.domain.entity.ListItem
import com.w4eret1ckrtb1tch.homework.domain.repository.ItemRepository
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val itemDataSource: ItemDataSource
) : ItemRepository {

    override fun getItems(): List<ListItem> {
        return itemDataSource.getItems()
    }
}
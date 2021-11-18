package com.w4eret1ckrtb1tch.homework.data.datasource

import com.w4eret1ckrtb1tch.homework.domain.entity.ListItem
import javax.inject.Inject

class ItemDataSourceLiveImpl @Inject constructor() : ItemDataSource {

    override fun getItems(): List<ListItem> {
        return DataLive.data
    }
}
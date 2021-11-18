package com.w4eret1ckrtb1tch.homework.data.datasource

import com.w4eret1ckrtb1tch.homework.domain.entity.ListItem

interface ItemDataSource {

    fun getItems(): List<ListItem>

}
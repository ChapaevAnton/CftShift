package com.w4eret1ckrtb1tch.homework.domain.repository

import com.w4eret1ckrtb1tch.homework.domain.entity.ListItem

interface ItemRepository {

    fun getItems(): List<ListItem>
}
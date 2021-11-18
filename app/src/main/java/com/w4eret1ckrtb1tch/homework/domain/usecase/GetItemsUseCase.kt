package com.w4eret1ckrtb1tch.homework.domain.usecase

import com.w4eret1ckrtb1tch.homework.domain.entity.ListItem
import com.w4eret1ckrtb1tch.homework.domain.repository.ItemRepository
import javax.inject.Inject

class GetItemsUseCase @Inject constructor(
    private val repository: ItemRepository
) {

    operator fun invoke(): List<ListItem> {
        return repository.getItems()
    }
}
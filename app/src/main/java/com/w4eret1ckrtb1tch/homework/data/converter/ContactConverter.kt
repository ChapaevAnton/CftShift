package com.w4eret1ckrtb1tch.homework.data.converter

import com.w4eret1ckrtb1tch.homework.data.db.ContactEntity
import com.w4eret1ckrtb1tch.homework.domain.entity.ContactDto

fun ContactEntity.toContactDto(): ContactDto {
    return ContactDto(
        id = id,
        name = name,
        number = number
    )
}

fun ContactDto.toContactEntity(): ContactEntity {
    return ContactEntity(
        id = id,
        name = name,
        number = number
    )
}

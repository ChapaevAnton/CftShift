package com.w4eret1ckrtb1tch.homework.domain.repository

import com.w4eret1ckrtb1tch.homework.domain.entity.ContactDto

interface ContentRepository {

    fun getContacts(): List<ContactDto>

}
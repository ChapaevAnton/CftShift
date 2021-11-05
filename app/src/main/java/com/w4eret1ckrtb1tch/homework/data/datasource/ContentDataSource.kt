package com.w4eret1ckrtb1tch.homework.data.datasource

import com.w4eret1ckrtb1tch.homework.domain.entity.ContactDto

interface ContentDataSource {

    fun getPhoneContacts(): List<ContactDto>

}
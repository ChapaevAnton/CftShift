package com.w4eret1ckrtb1tch.homework.data.datasource

import com.w4eret1ckrtb1tch.homework.domain.entity.ContactEntity

interface ContentDataSource {

    fun getPhoneContacts(): List<ContactEntity>

}
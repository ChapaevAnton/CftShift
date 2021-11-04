package com.w4eret1ckrtb1tch.homework.domain.datasource

import com.w4eret1ckrtb1tch.homework.domain.entity.ContactEntity

interface ContentResolver {

    fun getPhoneContacts(): List<ContactEntity>

}
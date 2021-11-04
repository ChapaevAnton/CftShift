package com.w4eret1ckrtb1tch.homework.domain.repository

import com.w4eret1ckrtb1tch.homework.domain.entity.ContactEntity

interface ContactRepository {

    fun getContacts(): List<ContactEntity>

    fun deleteContact(contact: ContactEntity)
}
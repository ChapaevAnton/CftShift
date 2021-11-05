package com.w4eret1ckrtb1tch.homework.domain.repository

import com.w4eret1ckrtb1tch.homework.domain.entity.ContactDto

interface ContactRepository {

    fun addContact(contact: ContactDto)

    fun addContacts(contacts: List<ContactDto>)

    fun removeContact(contact: ContactDto)

    fun getContacts(): List<ContactDto>

    fun countContacts(): Int

}
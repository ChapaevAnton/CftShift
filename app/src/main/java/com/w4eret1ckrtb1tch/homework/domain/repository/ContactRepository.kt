package com.w4eret1ckrtb1tch.homework.domain.repository

import com.w4eret1ckrtb1tch.homework.domain.entity.ContactEntity

interface ContactRepository {

    fun addContact(contact: ContactEntity)

    fun addContacts(contacts: List<ContactEntity>)

    fun removeContact(contact: ContactEntity)

    fun getContacts(): List<ContactEntity>

    fun countContacts(): Int

}
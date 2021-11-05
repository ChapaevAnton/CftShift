package com.w4eret1ckrtb1tch.homework.data.datasource

import com.w4eret1ckrtb1tch.homework.data.db.ContactEntity

interface ContactDataSource {

    fun addContact(contact: ContactEntity)

    fun addContacts(contacts: List<ContactEntity>)

    fun removeContact(contact: ContactEntity)

    fun getContacts(): List<ContactEntity>

    fun countContacts(): Int

}
package com.w4eret1ckrtb1tch.homework.data.repository

import com.w4eret1ckrtb1tch.homework.data.converter.toContactDto
import com.w4eret1ckrtb1tch.homework.data.converter.toContactEntity
import com.w4eret1ckrtb1tch.homework.data.datasource.ContactDataSource
import com.w4eret1ckrtb1tch.homework.domain.entity.ContactDto
import com.w4eret1ckrtb1tch.homework.domain.repository.ContactRepository

class ContactRepositoryImpl(
    private val contactDataSource: ContactDataSource
) : ContactRepository {

    override fun addContact(contact: ContactDto) {
        contactDataSource.addContact(contact.toContactEntity())
    }

    override fun addContacts(contacts: List<ContactDto>) {
        contactDataSource.addContacts(contacts.map { it.toContactEntity() })
    }

    override fun getContacts(): List<ContactDto> {
        return contactDataSource.getContacts().map { it.toContactDto() }
    }

    override fun removeContact(contact: ContactDto) {
        contactDataSource.removeContact(contact.toContactEntity())
    }

    override fun countContacts(): Int {
        return contactDataSource.countContacts()
    }

}
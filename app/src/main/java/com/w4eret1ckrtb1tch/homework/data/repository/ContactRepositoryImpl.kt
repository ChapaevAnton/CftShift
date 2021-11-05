package com.w4eret1ckrtb1tch.homework.data.repository

import com.w4eret1ckrtb1tch.homework.data.datasource.ContactDataSource
import com.w4eret1ckrtb1tch.homework.domain.entity.ContactEntity
import com.w4eret1ckrtb1tch.homework.domain.repository.ContactRepository

class ContactRepositoryImpl(
    private val contactDataSource: ContactDataSource
) : ContactRepository {

    override fun addContact(contact: ContactEntity) {
        contactDataSource.addContact(contact)
    }

    override fun addContacts(contacts: List<ContactEntity>) {
        contactDataSource.addContacts(contacts)
    }

    override fun getContacts(): List<ContactEntity> {
        return contactDataSource.getContacts()
    }

    override fun removeContact(contact: ContactEntity) {
        contactDataSource.removeContact(contact)
    }

    override fun countContacts(): Int {
        return contactDataSource.countContacts()
    }

}
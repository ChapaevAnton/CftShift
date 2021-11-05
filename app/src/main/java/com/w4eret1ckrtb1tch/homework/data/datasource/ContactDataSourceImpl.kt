package com.w4eret1ckrtb1tch.homework.data.datasource

import com.w4eret1ckrtb1tch.homework.data.db.AppDataBase
import com.w4eret1ckrtb1tch.homework.data.db.ContactEntity

class ContactDataSourceImpl(
    private val dataBase: AppDataBase
) : ContactDataSource {
    override fun addContact(contact: ContactEntity) {
        dataBase.contactsDao().insert(contact)
    }

    override fun addContacts(contacts: List<ContactEntity>) {
        dataBase.contactsDao().insertAll(contacts)
    }

    override fun removeContact(contact: ContactEntity) {
        dataBase.contactsDao().delete(contact)
    }

    override fun getContacts(): List<ContactEntity> {
        return dataBase.contactsDao().selectAll()
    }

    override fun countContacts(): Int {
        return dataBase.contactsDao().count()
    }
}
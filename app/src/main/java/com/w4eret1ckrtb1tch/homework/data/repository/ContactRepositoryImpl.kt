package com.w4eret1ckrtb1tch.homework.data.repository

import com.w4eret1ckrtb1tch.homework.data.db.AppDataBase
import com.w4eret1ckrtb1tch.homework.domain.datasource.ContentResolver
import com.w4eret1ckrtb1tch.homework.domain.entity.ContactEntity
import com.w4eret1ckrtb1tch.homework.domain.repository.ContactRepository

class ContactRepositoryImpl(
    private val database: AppDataBase,
    private val contentResolver: ContentResolver
) : ContactRepository {

    override fun getContacts(): List<ContactEntity> {
        if (getCount() == 0) {
            saveState(contentResolver.getPhoneContacts())
        }
        return restoreState()
    }

    override fun deleteContact(contact: ContactEntity) =
        database.contactsDao().delete(contact)

    private fun saveState(contacts: List<ContactEntity>) =
        database.contactsDao().insertAll(contacts)

    private fun restoreState(): List<ContactEntity> =
        database.contactsDao().selectAll()

    private fun getCount() = database.contactsDao().count()

}
package com.w4eret1ckrtb1tch.homework.data.datasource

import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import com.w4eret1ckrtb1tch.homework.domain.entity.ContactDto

class ContentDataSourceImpl(private val context: Context) : ContentDataSource {

    override fun getPhoneContacts(): List<ContactDto> {
        val contacts = mutableListOf<ContactDto>()
        val uri: Uri = ContactsContract.Contacts.CONTENT_URI
        val sort = "${ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME} ASC"
        val cursor =
            context.contentResolver.query(uri, null, null, null, sort)
        cursor?.use {
            if (cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    val uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                    val selection = "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID} =?"
                    val phoneCursor =
                        context.contentResolver.query(
                            uriPhone,
                            null,
                            selection,
                            arrayOf(id),
                            null
                        )
                    // TODO: 29.10.2021 while only the mobile phone is loaded
                    phoneCursor?.use {
                        if (phoneCursor.count > 0) {
                            if (phoneCursor.moveToNext()) {
                                val number =
                                    phoneCursor.getString(
                                        phoneCursor.getColumnIndex(
                                            ContactsContract.CommonDataKinds.Phone.NUMBER
                                        )
                                    )
                                when (phoneCursor.getInt(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE))) {
                                    ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE -> {
                                        val contact = ContactDto(name = name, number = number)
                                        contacts.add(contact)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return contacts
    }

}
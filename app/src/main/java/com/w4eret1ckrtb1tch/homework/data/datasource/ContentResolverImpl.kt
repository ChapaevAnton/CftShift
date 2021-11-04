package com.w4eret1ckrtb1tch.homework.data.datasource

import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import android.util.Log
import com.w4eret1ckrtb1tch.homework.domain.datasource.ContentResolver
import com.w4eret1ckrtb1tch.homework.domain.entity.ContactEntity

class ContentResolverImpl(private val context: Context) : ContentResolver {

    override fun getPhoneContacts(): List<ContactEntity> {
        val contacts = mutableListOf<ContactEntity>()
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
                            Log.d("TAG", "getContacts: ${phoneCursor.count}")
                            if (phoneCursor.moveToNext()) {
                                val number =
                                    phoneCursor.getString(
                                        phoneCursor.getColumnIndex(
                                            ContactsContract.CommonDataKinds.Phone.NUMBER
                                        )
                                    )
                                Log.d("TAG", "getContacts: $name $number ${phoneCursor.position}")
                                when (phoneCursor.getInt(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE))) {
                                    ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE -> {
                                        val contact = ContactEntity(name = name, number = number)
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
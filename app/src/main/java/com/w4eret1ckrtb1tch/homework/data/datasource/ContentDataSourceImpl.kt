package com.w4eret1ckrtb1tch.homework.data.datasource

import android.content.Context
import android.database.Cursor
import android.database.CursorWrapper
import android.net.Uri
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.provider.ContactsContract.Contacts
import com.w4eret1ckrtb1tch.homework.domain.entity.ContactDto

class ContentDataSourceImpl(private val context: Context) : ContentDataSource {
    // QUESTION: 05.11.2021 Разобраться как оптимизировать этот код? Как вынести cursor?
    override fun getPhoneContacts(): List<ContactDto> {
        val contacts = mutableListOf<ContactDto>()

        val uri: Uri = Contacts.CONTENT_URI
        val uriPhone = Phone.CONTENT_URI
        val sortOrder = "${Phone.DISPLAY_NAME} ASC"
        val phoneSelection = "${Phone.CONTACT_ID} =?"

        val cursor = query<ContactCursorWrapper>(uri, null, null, sortOrder)
        cursor.use { contactTable ->
            if (contactTable.count > 0) {
                contactTable.moveToFirst()
                while (!contactTable.isAfterLast) {
                    val contact = contactTable.getContact()
                    val selectionArgs = arrayOf(contact.id.toString())
                    val phoneCursor =
                        query<PhoneCursorWrapper>(uriPhone, phoneSelection, selectionArgs, null)
                    // TODO: 29.10.2021 while only the mobile phone is loaded
                    phoneCursor.use { phoneTable ->
                        if (phoneTable.count > 0) {
                            phoneTable.moveToFirst()
                            val number = phoneTable.getPhone()
                            val phoneContact = contact.copy(number = number)
                            contacts.add(phoneContact)
                        }
                    }
                    contactTable.moveToNext()
                }
            }
        }
        return contacts
    }

    private inline fun <reified T : CursorWrapper> query(
        uri: Uri,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): T {
        val cursor = context.contentResolver.query(uri, null, selection, selectionArgs, sortOrder)
        return T::class.java.getConstructor(Cursor::class.java).newInstance(cursor)
    }

}

class ContactCursorWrapper(cursor: Cursor) : CursorWrapper(cursor) {

    fun getContact(): ContactDto {
        val id = getLong(getColumnIndex(Contacts._ID))
        val name = getString(getColumnIndex(Contacts.DISPLAY_NAME))
        return ContactDto(id = id, name = name)
    }
}

class PhoneCursorWrapper(cursor: Cursor) : CursorWrapper(cursor) {

    fun getPhone(): String? {
        val number = getString(getColumnIndex(Phone.NUMBER))
        return when (getInt(getColumnIndex(Phone.TYPE))) {
            Phone.TYPE_MOBILE -> {
                number
            }
            else -> null
        }
    }
}
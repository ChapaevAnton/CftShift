package com.w4eret1ckrtb1tch.homework.presentation.fragments.list

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.ContactsContract
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.w4eret1ckrtb1tch.homework.data.db.AppDataBase
import com.w4eret1ckrtb1tch.homework.domain.model.ContactEntity
import com.w4eret1ckrtb1tch.homework.presentation.fragments.SingleLiveEvent

class ListViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AppDataBase.getDatabase(application.applicationContext)

    private val contacts = MutableLiveData<List<ContactEntity>>()
    fun getContacts(): LiveData<List<ContactEntity>> = contacts
    private val showPermission = SingleLiveEvent<Unit>()
    fun getPermission(): LiveData<Unit> = showPermission

    fun checkPermission() {
        if (isPermission()) {
            showPermission.value = Unit
        } else {
            contacts.value = restoreState()
        }
    }

    fun loadContacts() {
        loadContactsToDataBase()
        contacts.value = restoreState()
    }

    private fun isPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            getApplication(),
            Manifest.permission.READ_CONTACTS
        ) != PackageManager.PERMISSION_GRANTED
    }

    private fun saveState(contacts: List<ContactEntity>) {
        database.contactsDao().insertAll(contacts)
    }

    private fun restoreState(): List<ContactEntity> {
        return database.contactsDao().selectAll()
    }

    private fun loadContactsToDataBase() {
        val contacts = mutableListOf<ContactEntity>()
        val uri: Uri = ContactsContract.Contacts.CONTENT_URI
        val sort = "${ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME} ASC"
        val cursor =
            getApplication<Application>().contentResolver.query(uri, null, null, null, sort)
        cursor?.let {
            if (cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                    val name =
                        cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    val uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                    val selection = "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID} =?"
                    val phoneCursor =
                        getApplication<Application>().contentResolver.query(
                            uriPhone,
                            null,
                            selection,
                            arrayOf(id),
                            null
                        )
                    // TODO: 29.10.2021 while only the mobile phone is loaded
                    phoneCursor?.let {
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
                    phoneCursor?.close()
                }
            }
        }
        cursor?.close()
        saveState(contacts)
    }
}
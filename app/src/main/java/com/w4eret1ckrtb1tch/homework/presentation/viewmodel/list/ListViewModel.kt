package com.w4eret1ckrtb1tch.homework.presentation.viewmodel.list

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.w4eret1ckrtb1tch.homework.domain.entity.ContactEntity
import com.w4eret1ckrtb1tch.homework.domain.repository.ContactRepository
import com.w4eret1ckrtb1tch.homework.presentation.viewmodel.SingleLiveEvent
import java.util.concurrent.Executors

@SuppressLint("StaticFieldLeak")
class ListViewModel(
    private val context: Context,
    private val contactRepository: ContactRepository
) : ViewModel() {

    private val _contacts = MutableLiveData<List<ContactEntity>>()
    val contacts: LiveData<List<ContactEntity>> get() = _contacts
    private val _showPermission = SingleLiveEvent<Unit>()
    val showPermission: LiveData<Unit> get() = _showPermission

    fun checkPermission() {
        if (isPermissionGranted()) {
            _showPermission.value = Unit
        } else {
            getContacts()
        }
    }

    private fun isPermissionGranted(): Boolean {
        return ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_CONTACTS
        ) != PackageManager.PERMISSION_GRANTED
    }

    fun getContacts() {
        Executors.newSingleThreadExecutor().execute {
            _contacts.postValue(contactRepository.getContacts())
        }
    }

    fun deleteContact(contact: ContactEntity) {
        Executors.newSingleThreadExecutor().execute {
            contactRepository.deleteContact(contact)
        }
    }

}
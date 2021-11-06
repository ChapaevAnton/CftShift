package com.w4eret1ckrtb1tch.homework.presentation.viewmodel.list

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.lifecycle.*
import com.w4eret1ckrtb1tch.homework.domain.entity.ContactDto
import com.w4eret1ckrtb1tch.homework.domain.usecase.contacts.GetContactsUseCase
import com.w4eret1ckrtb1tch.homework.domain.usecase.contacts.RemoveContactUseCase
import com.w4eret1ckrtb1tch.homework.presentation.viewmodel.SingleLiveEvent
import kotlinx.coroutines.launch

@SuppressLint("StaticFieldLeak")
class ListViewModel(
    private val handle: SavedStateHandle,
    private val context: Context,
    private val getContactsUseCase: GetContactsUseCase,
    private val removeContactUseCase: RemoveContactUseCase
) : ViewModel() {

    private val _contacts = MutableLiveData<List<ContactDto>>()
    val contacts: LiveData<List<ContactDto>> get() = _contacts
    private val _showPermission = SingleLiveEvent<Unit>()
    val showPermission: LiveData<Unit> get() = _showPermission

    fun checkPermission() {
        if (isPermissionGranted()) {
            _showPermission.value = Unit
        } else {
            updateContacts()
        }
    }

    private fun isPermissionGranted(): Boolean {
        return ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_CONTACTS
        ) != PackageManager.PERMISSION_GRANTED
    }

    fun updateContacts() {
        viewModelScope.launch {
            _contacts.value = getContactsUseCase()
        }
    }

    fun removeContact(contact: ContactDto) {
        viewModelScope.launch {
            removeContactUseCase(contact)
        }
    }

    override fun onCleared() {
        _showPermission.call()
        super.onCleared()
    }
}
package com.w4eret1ckrtb1tch.homework.domain.usecase.contacts

import com.w4eret1ckrtb1tch.homework.domain.entity.ContactEntity
import com.w4eret1ckrtb1tch.homework.domain.repository.ContactRepository
import com.w4eret1ckrtb1tch.homework.domain.repository.ContentRepository

class GetContactsUseCase(
    private val contentRepository: ContentRepository,
    private val contactRepository: ContactRepository
) {

    operator fun invoke(): List<ContactEntity> {
        if (contactRepository.countContacts() == 0) {
            contactRepository.addContacts(contentRepository.getContacts())
        }
        return contactRepository.getContacts()
    }
}
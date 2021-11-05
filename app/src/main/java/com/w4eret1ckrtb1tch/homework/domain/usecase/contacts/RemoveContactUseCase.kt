package com.w4eret1ckrtb1tch.homework.domain.usecase.contacts

import com.w4eret1ckrtb1tch.homework.domain.entity.ContactDto
import com.w4eret1ckrtb1tch.homework.domain.repository.ContactRepository

class RemoveContactUseCase(
    private val contactRepository: ContactRepository
) {

    operator fun invoke(contact: ContactDto) = contactRepository.removeContact(contact)
}
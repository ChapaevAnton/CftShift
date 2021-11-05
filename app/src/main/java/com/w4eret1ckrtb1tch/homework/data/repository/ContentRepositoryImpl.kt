package com.w4eret1ckrtb1tch.homework.data.repository

import com.w4eret1ckrtb1tch.homework.data.datasource.ContentDataSource
import com.w4eret1ckrtb1tch.homework.domain.entity.ContactEntity
import com.w4eret1ckrtb1tch.homework.domain.repository.ContentRepository

class ContentRepositoryImpl(
    private val contentDataSource: ContentDataSource
) : ContentRepository {

    override fun getContacts(): List<ContactEntity> {
        return contentDataSource.getPhoneContacts()
    }
}
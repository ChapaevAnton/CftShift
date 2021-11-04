package com.w4eret1ckrtb1tch.homework.data.db

import androidx.room.*
import com.w4eret1ckrtb1tch.homework.domain.entity.ContactEntity

@Dao
interface ContactsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: ContactEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(contacts: List<ContactEntity>)

    @Delete(entity = ContactEntity::class)
    fun delete(contact: ContactEntity)

    @Query("SELECT * FROM contacts ORDER BY id ASC")
    fun selectAll(): List<ContactEntity>

    @Query("SELECT COUNT(*) FROM contacts")
    fun count():Int
}
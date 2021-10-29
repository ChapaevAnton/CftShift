package com.w4eret1ckrtb1tch.homework.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.w4eret1ckrtb1tch.homework.domain.model.ContactEntity


@Database(entities = [ContactEntity::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun contactsDao(): ContactsDao

    companion object {
        private var instance: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "app_db"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance as AppDataBase
        }
    }
}
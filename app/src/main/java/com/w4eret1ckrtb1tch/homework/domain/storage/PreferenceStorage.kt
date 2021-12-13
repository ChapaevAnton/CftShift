package com.w4eret1ckrtb1tch.homework.domain.storage

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

interface PreferenceStorage {

    fun readAuthToken(): Flowable<String>

    fun writeAuthToken(authToken: String): Completable

    companion object {
        const val PREFERENCE_STORAGE_FILE_NAME = "setting"
    }
}
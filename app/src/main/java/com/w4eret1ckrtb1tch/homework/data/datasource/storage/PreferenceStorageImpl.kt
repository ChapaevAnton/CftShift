package com.w4eret1ckrtb1tch.homework.data.datasource.storage

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.rxjava3.RxDataStore
import com.w4eret1ckrtb1tch.homework.domain.storage.PreferenceStorage
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


class PreferenceStorageImpl @Inject constructor(
    private val dataStore: @JvmSuppressWildcards RxDataStore<Preferences>
) : PreferenceStorage {

    override fun readAuthToken(): Flowable<String> {
        return dataStore.data()
            .map { prefs ->
                prefs[stringPreferencesKey(PreferenceStorage.AUTH_TOKEN_KEY)] ?: ""
            }.subscribeOn(Schedulers.io())
    }

    override fun writeAuthToken(authToken: String) {
        dataStore.updateDataAsync { prefs ->
            val mutablePreferences = prefs.toMutablePreferences()
            mutablePreferences[stringPreferencesKey(PreferenceStorage.AUTH_TOKEN_KEY)] = authToken
            return@updateDataAsync Single.just(mutablePreferences)
        }.subscribeOn(Schedulers.io()).subscribe()
    }
}
package com.w4eret1ckrtb1tch.homework.data.datasource.storage

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.rxjava3.RxDataStore
import com.w4eret1ckrtb1tch.homework.domain.storage.PreferenceStorage
import io.reactivex.rxjava3.core.Completable
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
                prefs[stringPreferencesKey(AUTH_TOKEN_KEY)] ?: ""
            }.subscribeOn(Schedulers.io())
    }

    override fun writeAuthToken(authToken: String): Completable {
        return dataStore.updateDataAsync { prefs ->
            val mutablePreferences = prefs.toMutablePreferences()
            mutablePreferences[stringPreferencesKey(AUTH_TOKEN_KEY)] = authToken
            return@updateDataAsync Single.just(mutablePreferences)
        }.flatMapCompletable { Completable.complete() }.subscribeOn(Schedulers.io())
    }

    private companion object {
        const val AUTH_TOKEN_KEY = "focus_start_auth_token_key"
    }
}
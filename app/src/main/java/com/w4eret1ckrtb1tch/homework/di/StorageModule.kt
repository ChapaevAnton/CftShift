package com.w4eret1ckrtb1tch.homework.di

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder
import androidx.datastore.rxjava3.RxDataStore
import com.w4eret1ckrtb1tch.homework.domain.storage.PreferenceStorage.Companion.PREFERENCE_STORAGE_FILE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Singleton
    @Provides
    fun providesRxDataStore(@ApplicationContext context: Context): @JvmSuppressWildcards RxDataStore<Preferences> =
        RxPreferenceDataStoreBuilder(context, PREFERENCE_STORAGE_FILE_NAME).build();
}
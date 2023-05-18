package com.lanazirot.simonsays.domain.services.interfaces

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface IDataStoreManager {
    suspend fun<T> save(key: Preferences.Key<T>, value: T)
    suspend fun<T> delete(key: Preferences.Key<T>): Boolean
    suspend fun<T> getPreference(key: Preferences.Key<T>): Flow<T>
    suspend fun<T> getPreference(key: Preferences.Key<T>, defaultValue: T): Flow<T>
}
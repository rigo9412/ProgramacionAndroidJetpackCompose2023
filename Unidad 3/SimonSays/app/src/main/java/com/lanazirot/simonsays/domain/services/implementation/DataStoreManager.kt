package com.lanazirot.simonsays.domain.services.implementation

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.lanazirot.simonsays.domain.services.interfaces.IDataStoreManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject


val Context.dataStore by preferencesDataStore(name = "SimonSaysDataStore")


class DataStoreManager @Inject constructor(context: Context) : IDataStoreManager {

    private val dataStore = context.dataStore

    companion object {
        val USER_THEME_KEY = booleanPreferencesKey("ui-theme")
    }

    override suspend fun <T> save(key: Preferences.Key<T>, value: T) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }

    override suspend fun <T> delete(key: Preferences.Key<T>): Boolean {
        dataStore.edit { preferences ->
            preferences.remove(key)
        }
        return true
    }

    override suspend fun <T> getPreference(key: Preferences.Key<T>): Flow<T> {
        return dataStore.data.map { preferences ->
            preferences[key] ?: throw Exception("No value found for key: $key")
        }
    }

    override suspend fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T): Flow<T> {
        return dataStore.data.catch {
            if (it is IOException) {
                emit(emptyPreferences())
            } else {
                throw it
            }
        }.map { preferences ->
            preferences[key] ?: defaultValue
        }
    }

}
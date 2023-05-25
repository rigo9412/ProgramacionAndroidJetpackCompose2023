package com.ezequiel.simondice.domain

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SimonStore(private val context : Context){
    companion object{
        private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "STORE-SIMON")
        private val USER_THEME_CONFIG_KEY = booleanPreferencesKey("theme_ui")
        private val USER_TOKEN = stringPreferencesKey("token-access")
    }

    val getThemeConfig: Flow<Boolean> = context.dataStore.data.map { preferences ->
        preferences[USER_THEME_CONFIG_KEY] ?: false
    }

    suspend fun saveThemeConfig(theme : Boolean){
        context.dataStore.edit { preferences ->
            preferences[USER_THEME_CONFIG_KEY] = theme
        }
    }

    val getUserAccessToken : Flow<String> = context.dataStore.data.map { preferences ->
        preferences[USER_TOKEN] ?: ""
    }

    suspend fun setUserAccessToken(userAccessToken: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_TOKEN] = userAccessToken
        }
    }
}
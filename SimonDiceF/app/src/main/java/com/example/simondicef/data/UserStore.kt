package com.example.simondicef.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserStore(private val context: Context){
    companion object{
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("theme")
        private val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")
    }

    val getDarkModeValue: Flow<Boolean> = context.dataStore.data.map{
        it[DARK_MODE_KEY] ?: false
    }

    suspend fun saveDarkModeValue(darkMode: Boolean){
        context.dataStore.edit{
            it[DARK_MODE_KEY] = darkMode
        }
    }
}
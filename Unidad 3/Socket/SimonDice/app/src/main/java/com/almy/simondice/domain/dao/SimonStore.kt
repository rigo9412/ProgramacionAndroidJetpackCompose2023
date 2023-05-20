package com.almy.simondice.domain.dao

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.*
import kotlinx.coroutines.flow.map

import java.util.concurrent.Flow
import java.util.prefs.PreferenceChangeEvent
import java.util.prefs.Preferences

class SimonStore(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("STORE-SIMON")
        private val USER_THEME_CONFIG_KEY = booleanPreferencesKey("theme_ui")
        private val USER_TOKEN = stringPreferencesKey("token-access")
    }
    val getThemeConfig: Flow<Boolean> = context.dataStore.data.map {preferences -> preferences[USER_THEME_CONFIG_KEY]?: false
    }
    suspend fun  saveThemeConfig(theme: Boolean){
        context.dataStore.edit {preferences -> preferences[USER_THEME_CONFIG_KEY] = theme
        }

    }
    val getAccessToken: Flow<String> = context.dataStore.data.map { preferences -> preferences[USER_TOKEN]?:""
    }

    suspend fun saveAcessToken(theme: String){
        context.dataStore.edit {preferences -> preferences[USER-TOKEN] = theme
        }
    }

}
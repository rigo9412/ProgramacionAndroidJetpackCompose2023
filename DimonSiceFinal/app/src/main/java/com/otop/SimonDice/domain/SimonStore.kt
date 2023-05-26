package com.otop.SimonDice.domain

import android.content.Context
import kotlinx.coroutines.flow.Flow

class SimonStore(private val context:Context) {





    companion object{
//        private val Context
        private val USER_THEME_CONFIG_KEY = booleanPreferencesKey("theme_ui")
        private val USER_TOKEN = stringPreferenceKey("token-access")
    }

    val getThemeConfig(theme: Boolean){
        context.dataStore.edit {
            preferences[USER_THEME_CONFIG_KEY] = theme
        }
    }

    val getAccessToken: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[USER_TOKEN] ?: ""
    }

    suspend fun saveAccessToken(theme: String){
        context.dataSource.edit{ preferences ->
            preferences[USER_TOKEN] =theme1

        }
    }
}
package com.otop.datastore.ui.theme

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "theme_prefs")

class ThemeManager(private val context: Context) {
    private val dataStore = context.dataStore

    private object Keys {
        val IS_DARK_THEME = booleanPreferencesKey("is_dark_theme")
    }

    var isDarkTheme by mutableStateOf(false)

    init {
        runBlocking {
            loadThemePreference()
        }
    }

    private suspend fun loadThemePreference() {
        val preferences = dataStore.data.first()
        isDarkTheme = preferences[Keys.IS_DARK_THEME] ?: false
    }

    fun toggleTheme() {
        isDarkTheme = !isDarkTheme

        context.run {
            runBlocking {
                dataStore.edit { preferences ->
                    preferences[Keys.IS_DARK_THEME] = isDarkTheme
                }
            }
        }
    }

    val themeFlow: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[Keys.IS_DARK_THEME] ?: false
    }
}

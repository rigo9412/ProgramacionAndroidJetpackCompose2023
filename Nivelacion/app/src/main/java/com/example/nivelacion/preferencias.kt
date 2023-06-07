package com.example.nivelacion

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

const val MERCHANT_DATASTORE = "form_datastore"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = MERCHANT_DATASTORE)

class DataStoreManager (val context: Context) {

    companion object {
        val LimiteN = stringPreferencesKey("LimiteN")

    }

    suspend fun saveToDataStore(N:String) {
        context.dataStore.edit {
            it[LimiteN] = N

        }
    }

    fun getFromDataStore() = context.dataStore.data.map {
       it[LimiteN] ?: ""
    }

    suspend fun clearDataStore() = context.dataStore.edit {
        it.clear()
    }

}
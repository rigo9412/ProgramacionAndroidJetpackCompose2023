package com.program.nivelacion.ui.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

const val MERCHANT_DATASTORE = "form_datastore"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = MERCHANT_DATASTORE)

class DataStoreManager (val context: Context){

    companion object {
        val VALOR_N = stringPreferencesKey("ValorN")
    }

    suspend fun guardarDataStore(data: UiStateData) {
        context.dataStore.edit {
           it[VALOR_N] = data.Nveces
        }
    }

    fun consultarDataStore() = context.dataStore.data.map {
        UiStateData(
            Nveces = it[VALOR_N] ?: ""
        )
    }

    suspend fun limpiarDataStore() = context.dataStore.edit {
        it.clear()
    }

}
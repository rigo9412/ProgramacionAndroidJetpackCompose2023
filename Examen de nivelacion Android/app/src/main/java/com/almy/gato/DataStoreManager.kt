package com.almy.gato

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.almy.memorama.Suma
import kotlinx.coroutines.flow.map


const val MERCHANT_DATASTORE = "form_datastore"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = MERCHANT_DATASTORE)

class DataStoreManager (val context: Context) {

    companion object {
        val N = stringPreferencesKey("N")
    }

    suspend fun saveToDataStore(formData: Suma, darkTheme: Boolean) {
        context.dataStore.edit {
            it[N] = formData.N.toString()
        }
    }

    fun getFromDataStore() = context.dataStore.data.map {
        Suma(
            N = (it[N] ?: 0) as Int,
        )
    }


    suspend fun clearDataStore() = context.dataStore.edit {
        it.clear()
    }

}
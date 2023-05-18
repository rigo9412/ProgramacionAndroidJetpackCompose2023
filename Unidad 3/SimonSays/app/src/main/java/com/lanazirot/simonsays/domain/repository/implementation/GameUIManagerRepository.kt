package com.lanazirot.simonsays.domain.repository.implementation

import com.lanazirot.simonsays.domain.repository.interfaces.IGameUIManagerRepository
import com.lanazirot.simonsays.domain.services.implementation.DataStoreManager
import com.lanazirot.simonsays.domain.services.interfaces.IDataStoreManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


class GameUIManagerRepository @Inject constructor(private val dataStoreManager: IDataStoreManager) : IGameUIManagerRepository{
    override suspend fun isDarkTheme(): Flow<Boolean> {
        return dataStoreManager.getPreference(DataStoreManager.USER_THEME_KEY, false)
    }

    override suspend fun setDarkTheme(isDarkTheme: Boolean) {
        dataStoreManager.save(DataStoreManager.USER_THEME_KEY, isDarkTheme)
    }
}
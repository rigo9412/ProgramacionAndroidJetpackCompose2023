package com.lanazirot.simonsays.domain.repository.interfaces

import kotlinx.coroutines.flow.Flow

interface IGameUIManagerRepository {
    suspend fun isDarkTheme(): Flow<Boolean>
    suspend fun setDarkTheme(isDarkTheme: Boolean)
}
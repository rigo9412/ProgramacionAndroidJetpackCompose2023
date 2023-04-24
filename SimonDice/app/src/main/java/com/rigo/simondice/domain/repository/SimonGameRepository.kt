package com.rigo.simondice.domain.repository

import com.rigo.simondice.domain.models.Player
import com.rigo.simondice.domain.service.network.IApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SimonGameRepository @Inject constructor( val apiService: IApiService) {

    fun getTop(): Flow<List<Player>> = flow {
        val result = mutableListOf<Player>()
        val response = apiService.getTop()
        response.data.map {
            result.add(Player(it.attributes?.name ?: "?????",it.attributes?.value ?: 0,1))
        }
        emit(result)
    }.flowOn(Dispatchers.IO)
}
package com.example.simondice.repository

import com.example.simondice.domain.models.Attributes
import com.example.simondice.domain.models.Data
import com.example.simondice.domain.models.Player
import com.example.simondice.domain.models.PostRequestTop
import com.example.simondice.domain.service.network.IApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SimonGameRepository
@Inject constructor(val apiService: IApiService) {
        fun getTop(): Flow<List<Player>> = flow {
            val result = mutableListOf<Player>()
            val response = apiService.getTop()
            response.data.map{
                result.add(Player(it.id,it.attributes.name ?: "",it.attributes.value?:0,0))
            }
            emit(result)
        }.flowOn(Dispatchers.IO)

       fun postTop(player: Player): Flow<List<Player>> = flow {
            val result = mutableListOf<Player>()
            val response = apiService.postTop()
            response.data.map{
                result.add(Player(it.id,it.attributes.name ?: "",it.attributes.value?:0,0))
            }
            emit(result)
        }.flowOn(Dispatchers.IO)
}

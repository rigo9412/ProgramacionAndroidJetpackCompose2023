package com.lanazirot.simonsays.domain.services.implementation

import com.lanazirot.simonsays.domain.dao.IPlayerDAO
import com.lanazirot.simonsays.domain.model.Player
import com.lanazirot.simonsays.domain.model.api.post.Data
import com.lanazirot.simonsays.domain.model.api.post.response.ResponsePost
import com.lanazirot.simonsays.domain.repository.interfaces.IApiRepository
import com.lanazirot.simonsays.domain.services.interfaces.IApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class ApiService(private val apiRepository: IApiRepository, private val playerDAO: IPlayerDAO) :
    IApiService {
    override suspend fun getTopTenScores(): Flow<List<Player>> {
        val apiResponse = apiRepository.getTopTenScores()
        val players = mutableListOf<Player>()
        if (apiResponse != null) {
            apiResponse.data.forEach {
                players.add(
                    Player(
                        it.id,
                        it.attributes.name,
                        it.attributes.value ?: 0,
                        it.attributes.level
                    )
                )
            }
            playerDAO.insertAll(players)
        }
        return flow { emit(players) }
    }

    override suspend fun postScore(score: Data): ResponsePost {
        val player = Player(score.data.id, score.data.name, score.data.value, score.data.level)
        playerDAO.insert(player)
        return apiRepository.postScore(score)
    }
}
package com.lanazirot.simonsays.domain.services.interfaces

import com.lanazirot.simonsays.domain.model.Player
import com.lanazirot.simonsays.domain.model.Score
import com.lanazirot.simonsays.domain.model.api.post.Data
import com.lanazirot.simonsays.domain.model.api.post.response.ResponsePost
import kotlinx.coroutines.flow.Flow


interface IApiService {
    suspend fun getTopTenScores(): Flow<List<Player>>
    suspend fun postScore(score: Data): ResponsePost
}
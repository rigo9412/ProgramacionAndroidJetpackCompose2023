package com.lanazirot.simonsays.domain.services.implementation

import com.lanazirot.simonsays.domain.model.Score
import com.lanazirot.simonsays.domain.model.api.post.Data
import com.lanazirot.simonsays.domain.model.api.post.response.ResponsePost
import com.lanazirot.simonsays.domain.repository.interfaces.IApiRepository
import com.lanazirot.simonsays.domain.services.interfaces.IApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class ApiService(private val apiRepository: IApiRepository) : IApiService {
    override suspend fun getTopTenScores(): Flow<List<Score>> {
        val apiResponse = apiRepository.getTopTenScores()
        val scores = mutableListOf<Score>()
        if (apiResponse != null) {
            apiResponse.data.forEach {
                scores.add(Score(it.attributes.value?:0 , it.attributes.name!!))
            }
        }
        return flow { emit(scores) }
    }

    override suspend fun postScore(score: Data): ResponsePost {
        return  apiRepository.postScore(score)
    }
}
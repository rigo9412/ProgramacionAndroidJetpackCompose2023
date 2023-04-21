package com.lanazirot.simonsays.domain.services.interfaces

import com.lanazirot.simonsays.domain.model.Score

interface IGameManager {
    fun addToScoreLog(score: Score)
    fun getTopTenScores() : List<Score>
    fun scoreIsGoingToBeInTheTopTen(score: Score) : Boolean
    fun printScoreLog() : String
}
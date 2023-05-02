package com.lanazirot.simonsays.domain.services.implementation

import com.lanazirot.simonsays.domain.model.Score
import com.lanazirot.simonsays.domain.services.interfaces.IGameManager

class GameManager :IGameManager {
    private val scoreLog = mutableListOf<Score>()
    override fun addToScoreLog(score: Score) {
        scoreLog.add(score)
    }

    override fun getTopTenScores(): List<Score> {
        return scoreLog.sortedByDescending { it.score }.take(10)
    }

    override fun scoreIsGoingToBeInTheTopTen(score: Score): Boolean {
        return score.score !=0 && (scoreLog.size < 10 || scoreLog.sortedByDescending { it.score }.last().score < score.score)
    }

    override fun printScoreLog(): String {
        var scoreLogString = "Lista de Scores: \n"
        for (score in scoreLog) {
            scoreLogString += score.name + " " + score.score + "\n"
        }

        return scoreLogString
    }
}
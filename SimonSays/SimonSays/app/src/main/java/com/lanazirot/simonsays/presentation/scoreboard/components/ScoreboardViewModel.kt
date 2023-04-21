package com.lanazirot.simonsays.presentation.scoreboard.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lanazirot.simonsays.domain.model.Score
import com.lanazirot.simonsays.domain.services.interfaces.IGameManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScoreboardViewModel @Inject constructor(
    private val gameManager: IGameManager
) : ViewModel() {
    init {
//        addToScoreLog(Score(100, "Luis"))
//        addToScoreLog(Score(50, "Pedro"))
//        addToScoreLog(Score(345, "maria"))
//        addToScoreLog(Score(44, "Roberto"))
    }

    fun addToScoreLog(score: Score) {
        gameManager.addToScoreLog(score)
    }
    fun getTopTenScores() : List<Score> {
        return gameManager.getTopTenScores()
    }
    fun scoreIsGoingToBeInTheTopTen(score: Score) : Boolean {
        return gameManager.scoreIsGoingToBeInTheTopTen(score)
    }
    fun printScoreLog() : String {
        return gameManager.printScoreLog()
    }
}
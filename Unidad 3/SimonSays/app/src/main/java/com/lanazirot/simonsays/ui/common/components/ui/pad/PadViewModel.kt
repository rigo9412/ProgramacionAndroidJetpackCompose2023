package com.lanazirot.simonsays.ui.common.components.ui.pad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lanazirot.simonsays.domain.model.*
import com.lanazirot.simonsays.domain.model.api.post.DataPost
import com.lanazirot.simonsays.domain.services.interfaces.IApiService
import com.lanazirot.simonsays.domain.services.interfaces.IGameManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random
import com.lanazirot.simonsays.domain.model.api.post.Data
import com.lanazirot.simonsays.domain.services.implementation.CustomSocketHandler
import java.util.logging.SocketHandler


@HiltViewModel
class PadViewModel @Inject constructor(
    private val gameManager: IGameManager,
    private val apiService: IApiService
) : ViewModel() {
    private val _pad = MutableStateFlow(PadState())
    val pad = _pad.asStateFlow()
    private val colorList = SimonColorPad.values().filter { it != SimonColorPad.NONE }

    init {
        _pad.value = PadState(gameStatus = GameStatus.HOLD)
        //Listen to the socket
        CustomSocketHandler.getInstance().on("notification.top") {
            println("New score received")
            println(it)
        }
    }


    private fun addStepColorToSequence() {
        var randomGenerator = Random(System.currentTimeMillis())
        var result = randomGenerator.nextInt(0, 3)
        var randomColor = colorList[result]
        _pad.value = PadState(
            player = _pad.value.player,
            currentStep = _pad.value.currentStep + 1,
            pad = Pad(
                colorSequence = _pad.value.pad?.colorSequence?.plus(
                    StepColorAction(
                        _pad.value.currentStep,
                        randomColor
                    )
                )
            ),
            gameStatus = _pad.value.gameStatus
        )
    }

    fun postScore(score: Score) {
        val newDataPost = DataPost(name = score.name, value = score.score, level = 1)
        viewModelScope.launch {
            val post = apiService.postScore(Data(data = newDataPost))
            newDataPost.id = post.data.id
            CustomSocketHandler.getInstance().emit("newTop", newDataPost.toJSON())
        }
    }

    fun gameStart(player: Player) {
        _pad.value = PadState(
            player = player,
            pad = com.lanazirot.simonsays.domain.model.Pad(),
            gameStatus = GameStatus.PAD_YELLING
        )
        this.addStepColorToSequence()
    }

    fun gamePlaying() {
        _pad.value = PadState(
            gameStatus = GameStatus.PLAYING,
            pad = _pad.value.pad,
            player = _pad.value.player,
            currentStep = _pad.value.currentStep
        )
    }

    private fun gameOver() {
        //Antes de que pierdas, vamos a validar si tu score puede entrar
        if (gameManager.scoreIsGoingToBeInTheTopTen(
                Score(
                    score = _pad.value.player?.score ?: 0,
                    name = ""
                )
            )
        ) {
            _pad.value = PadState(
                gameStatus = GameStatus.GAME_OVER,
                isGoingToScoreboard = true,
                player = _pad.value.player
            )
        } else {
            _pad.value = PadState(gameStatus = GameStatus.GAME_OVER, isGoingToScoreboard = false)
        }
    }


    fun setName(name: String) {
        gameManager.addToScoreLog(Score(score = _pad.value.player?.score ?: 0, name = name))
        _pad.value = PadState(gameStatus = GameStatus.GAME_OVER, isGoingToScoreboard = false)
    }

    fun compareStep(inputStep: StepColorAction) {
        val stepNumber = inputStep.order

        val isStepCorrect =
            _pad.value.pad?.colorSequence?.get(stepNumber - 1)?.color == inputStep.color
        if (!isStepCorrect) {
            _pad.value =
                PadState(player = _pad.value.player?.score?.let { _pad.value.player?.copy(score = it) })
            this.gameOver()
        } else if (stepNumber == _pad.value.pad?.colorSequence?.size) {
            this.addStepColorToSequence()
            _pad.value = PadState(
                player = _pad.value.player?.copy(score = _pad.value.player?.score?.plus(1) ?: 0),
                currentStep = 1,
                pad = _pad.value.pad,
                gameStatus = GameStatus.PAD_YELLING
            )
        } else {
            _pad.value = PadState(
                player = _pad.value.player,
                currentStep = _pad.value.currentStep + 1,
                pad = _pad.value.pad,
                gameStatus = _pad.value.gameStatus
            )
        }
    }

}
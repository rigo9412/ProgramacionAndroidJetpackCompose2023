package com.lanazirot.simonsays.presentation.pad

import androidx.lifecycle.ViewModel
import com.lanazirot.simonsays.domain.model.*
import com.lanazirot.simonsays.domain.services.interfaces.IGameManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class PadViewModel @Inject constructor( private val gameManager: IGameManager) : ViewModel() {
    private val _pad = MutableStateFlow(PadState())
    val pad = _pad.asStateFlow()
    private val colorList = SimonColorPad.values().filter { it != SimonColorPad.NONE }

    init {
        _pad.value = PadState(gameStatus = GameStatus.HOLD)
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

    fun gameStart(player: Player) {
        _pad.value = PadState(player = player, pad = Pad(), gameStatus = GameStatus.PAD_YELLING)
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
        _pad.value = PadState(gameStatus = GameStatus.GAME_OVER)
    }

    fun keepPlaying(){
        _pad.value = _pad.value.copy(isGoingToScoreboard = false)
    }

    fun addToScoreboard(){
        gameManager.addToScoreLog(Score(score = _pad.value.player?.score ?: 0, name = _pad.value.player?.name ?: ""))
    }

    fun setName(name: String){
        _pad.value = _pad.value.copy(player = _pad.value.player?.copy(name = name))
        this.addStepColorToSequence()
        gameManager.addToScoreLog(Score(score = _pad.value.player?.score ?: 0, name = _pad.value.player?.name ?: ""))
    }

    fun compareStep(inputStep: StepColorAction) {
        val stepNumber = inputStep.order

        val isStepCorrect =
            _pad.value.pad?.colorSequence?.get(stepNumber - 1)?.color == inputStep.color
        if (!isStepCorrect) this.gameOver() else if (stepNumber == _pad.value.pad?.colorSequence?.size) {

            if(gameManager.scoreIsGoingToBeInTheTopTen(Score(score = _pad.value.player?.score?.plus(1) ?: 0, name = ""))){
                _pad.value = _pad.value.copy(isGoingToScoreboard = true)
            }else{
                this.addStepColorToSequence()
                _pad.value = PadState(
                    player = _pad.value.player?.copy(
                        score = _pad.value.player?.score?.plus(1) ?: 0
                    ),
                    currentStep = 1,
                    pad = _pad.value.pad,
                    gameStatus = GameStatus.PAD_YELLING,
                )
            }

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
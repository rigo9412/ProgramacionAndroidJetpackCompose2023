package com.lanazirot.simonsays.presentation.pad

import android.util.Log
import androidx.lifecycle.ViewModel
import com.lanazirot.simonsays.domain.model.Pad
import com.lanazirot.simonsays.domain.model.SimonColorPad
import com.lanazirot.simonsays.domain.model.Player
import com.lanazirot.simonsays.domain.model.StepColorAction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class PadViewModel @Inject constructor() : ViewModel() {
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
               colorSequence = _pad.value.pad?.colorSequence?.plus(StepColorAction(_pad.value.currentStep, randomColor))
            ),
            gameStatus = _pad.value.gameStatus
        )
    }

    fun gameStart(player: Player) {
        _pad.value = PadState(player = player, pad = Pad(), gameStatus = GameStatus.PAD_YELLING)
        this.addStepColorToSequence()
    }

    fun gamePlaying() {
        _pad.value = PadState(gameStatus = GameStatus.PLAYING, pad = _pad.value.pad, player = _pad.value.player, currentStep = _pad.value.currentStep)
    }

    private fun gameOver() {
        _pad.value = PadState(gameStatus = GameStatus.GAME_OVER)
    }


    fun compareStep(inputStep: StepColorAction) {
        val stepNumber = inputStep.order

        val isStepCorrect = _pad.value.pad?.colorSequence?.get(stepNumber - 1)?.color == inputStep.color
        if (!isStepCorrect) this.gameOver() else if (stepNumber == _pad.value.pad?.colorSequence?.size) {
            this.addStepColorToSequence()
            _pad.value = PadState(
                player = _pad.value.player?.copy(score = _pad.value.player?.score?.plus(1) ?: 0),
                currentStep = 1,
                pad = _pad.value.pad,
                gameStatus = GameStatus.PAD_YELLING
            )
        }else{
            _pad.value = PadState(
                player = _pad.value.player,
                currentStep = _pad.value.currentStep + 1,
                pad = _pad.value.pad,
                gameStatus = _pad.value.gameStatus
            )
        }
    }

}
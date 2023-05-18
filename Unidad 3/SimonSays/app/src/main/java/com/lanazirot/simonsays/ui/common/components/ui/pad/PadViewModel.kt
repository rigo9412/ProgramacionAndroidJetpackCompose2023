package com.lanazirot.simonsays.ui.common.components.ui.pad

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lanazirot.simonsays.domain.model.Pad
import com.lanazirot.simonsays.domain.model.Player
import com.lanazirot.simonsays.domain.model.Score
import com.lanazirot.simonsays.domain.model.StepColorAction
import com.lanazirot.simonsays.domain.model.api.post.Data
import com.lanazirot.simonsays.domain.model.api.post.DataPost
import com.lanazirot.simonsays.domain.model.enums.AppStatus
import com.lanazirot.simonsays.domain.model.enums.SimonColorPad
import com.lanazirot.simonsays.domain.repository.interfaces.IGameUIManagerRepository
import com.lanazirot.simonsays.domain.services.implementation.CustomSocketHandler
import com.lanazirot.simonsays.domain.services.interfaces.IApiService
import com.lanazirot.simonsays.domain.services.interfaces.IGameManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random


@HiltViewModel
class PadViewModel @Inject constructor(
    private val gameManager: IGameManager, private val apiService: IApiService,
    private val gameUIManagerRepository: IGameUIManagerRepository
) : ViewModel() {
    private val _pad = MutableStateFlow(PadState())
    val pad = _pad.asStateFlow()
    private val colorList = SimonColorPad.values().filter { it != SimonColorPad.NONE }

    private val _appStatus: MutableStateFlow<AppStatus> = MutableStateFlow(AppStatus.RUNNING)
    val appStatus = _appStatus

    private val _uiTheme = MutableStateFlow(true)
    val uiTheme = _uiTheme.asStateFlow()

    init {
        _pad.value = PadState(gameStatus = GameStatus.HOLD)
        CustomSocketHandler.getInstance().on("notification.top") {
            val data = it as Array<Any>
            viewModelScope.launch {
                _appStatus.value = AppStatus.NOTIFICATION(data[0].toString())
                delay(1000)
                _appStatus.value = AppStatus.RUNNING
            }

        }
        loadThemeValue()
    }

    private fun loadThemeValue() {
        viewModelScope.launch(Dispatchers.IO) {
            gameUIManagerRepository.isDarkTheme().collect {
                _uiTheme.value = it
            }
        }
    }

    fun setDarkTheme(isDarkTheme: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            gameUIManagerRepository.setDarkTheme(isDarkTheme)
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

    private fun postScore(score: Score) {
        val newDataPost = DataPost(name = score.name, value = score.score, level = score.score)
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


    fun setPlayerToScoreboard(name: String) {
        _pad.value.player?.score?.let { postScore(Score(it, name)) }
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
package com.rigo.simondice.ui.game

import android.media.MediaPlayer
import androidx.lifecycle.ViewModel
import com.rigo.simondice.domain.models.Action
import com.rigo.simondice.domain.models.Game
import com.rigo.simondice.domain.models.Player
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random

class GameViewModel: ViewModel() {
    private val INCREMENT_BY_LEVEL = 1
    private val INCREMENT_BY_SCORE = 10
    private val _uiState =
        MutableStateFlow<GameScreenState>(GameScreenState.Init)
    val uiState: StateFlow<GameScreenState> = _uiState

    private val _uiStateData = MutableStateFlow<GameData>(GameData())
    val uiStateData: StateFlow<GameData> = _uiStateData

    val endSpeak get() = uiStateData.value.listActions.isNotEmpty() && uiStateData.value.listActions.lastIndex < uiStateData.value.currentActionSimonIndex
    val currentActionSimonIndex get() = uiStateData.value.currentActionSimonIndex

    fun onEvent(event: GameEvent) {
        when(event){
            is GameEvent.PressButtonEvent ->  _uiStateData.value = _uiStateData.value.copy(actionPlayer = event.type)
            GameEvent.StartGame -> start()
        }
    }

    fun getCurrentAction(): Action {
        return if(_uiStateData.value.listActions.isNotEmpty()
            && _uiStateData.value.currentActionSimonIndex >= 0
            && _uiStateData.value.currentActionSimonIndex <= _uiStateData.value.listActions.lastIndex)
            _uiStateData.value.listActions[_uiStateData.value.currentActionSimonIndex] else Action.NO_ACTION
    }

    suspend fun PlayerPlays(currentActionPlayer:Action,greenAudio: MediaPlayer,redAudio: MediaPlayer,yellowAudio: MediaPlayer,blueAudio: MediaPlayer){
        coroutineScope {
            if (endSpeak && currentActionPlayer != Action.NO_ACTION && !uiStateData.value.currentActionOn) {
                when (currentActionPlayer) {
                    Action.PRESS_GREEN_BUTTON -> playAudio(greenAudio)
                    Action.PRESS_RED_BUTTON ->  playAudio(redAudio)
                    Action.PRESS_YELLOW_BUTTON ->  playAudio(yellowAudio)
                    Action.PRESS_BLUE_BUTTON ->  playAudio(blueAudio)

                    else -> {
                        //NO SUENA
                    }
                }
                _uiStateData.value = _uiStateData.value.copy(currentActionOn= true)
                delay(300)
                validateAction(currentActionPlayer)

                //currentActionSimonIndexState = game.currentActionSimonIndex
                when (currentActionPlayer) {
                    Action.PRESS_GREEN_BUTTON ->  pauseAudio(greenAudio)
                    Action.PRESS_RED_BUTTON ->  pauseAudio(redAudio)
                    Action.PRESS_YELLOW_BUTTON ->  pauseAudio(yellowAudio)
                    Action.PRESS_BLUE_BUTTON ->  pauseAudio(blueAudio)
                    else -> {
                        //NO SUENA
                    }
                }
                _uiStateData.value = _uiStateData.value.copy(actionPlayer = Action.NO_ACTION,currentActionOn= false)
            }
        }

    }

    suspend fun GameSpeak(greenAudio: MediaPlayer,redAudio: MediaPlayer,yellowAudio: MediaPlayer,blueAudio: MediaPlayer) {
        coroutineScope {
            val action = getCurrentAction()
            if (!uiStateData.value.currentActionOn && !endSpeak) {
                when (action) {
                    Action.PRESS_GREEN_BUTTON -> playAudio(greenAudio)
                    Action.PRESS_RED_BUTTON -> playAudio(redAudio)
                    Action.PRESS_YELLOW_BUTTON -> playAudio(yellowAudio)
                    Action.PRESS_BLUE_BUTTON -> playAudio(blueAudio)
                    else -> {
                        //NO SUENA
                    }
                }
                _uiStateData.value = _uiStateData.value.copy(currentActionOn = true)
                delay(1000)
                moveToNextAction()
                when (action) {
                    Action.PRESS_GREEN_BUTTON -> pauseAudio(greenAudio)
                    Action.PRESS_RED_BUTTON -> pauseAudio(redAudio)
                    Action.PRESS_YELLOW_BUTTON -> pauseAudio(yellowAudio)
                    Action.PRESS_BLUE_BUTTON -> pauseAudio(blueAudio)
                    else -> {
                        //NO SUENA
                    }
                }
            }
        }

    }



    private fun start()  {
        _uiStateData.value = GameData()
    }

    fun saveRecord(player: Player): Player {

        _uiStateData.value = GameData()
        return player
    }

    private fun validateAction(action: Action){
        if(_uiStateData.value.listActions[_uiStateData.value.currentActionPlayerIndex] != action){
            endGame()
        }
        if(_uiStateData.value.listActions.lastIndex == _uiStateData.value.currentActionPlayerIndex){
            levelUp()
        }else{
            nextAction()
        }
    }

    private fun moveToNextAction(): Action{
        if(_uiStateData.value.listActions.isEmpty() || _uiStateData.value.currentActionSimonIndex > _uiStateData.value.listActions.lastIndex) return Action.NO_ACTION
        if(_uiStateData.value.currentActionSimonIndex == _uiStateData.value.listActions.lastIndex){
            _uiStateData.value = _uiStateData.value.copy(currentActionOn = false,currentActionSimonIndex = _uiStateData.value.currentActionSimonIndex +1 )
            return Action.NO_ACTION
        }
        _uiStateData.value = _uiStateData.value.copy(currentActionOn = false,currentActionSimonIndex = _uiStateData.value.currentActionSimonIndex +1 )
        return _uiStateData.value.listActions[_uiStateData.value.currentActionSimonIndex]

    }

    private fun endGame(){
//        _uiStateData.value = _uiStateData.value.copy(
//            currentActionPlayerIndex = _uiStateData.value.currentActionPlayerIndex+1)
        _uiStateData.value = GameData()
    }

    private fun nextAction(){
        _uiStateData.value = _uiStateData.value.copy(
            currentActionPlayerIndex = _uiStateData.value.currentActionPlayerIndex+1)
    }

    private fun levelUp(){
        _uiStateData.value = _uiStateData.value.copy(
            maxSteps = _uiStateData.value.maxSteps + 1,
            level = _uiStateData.value.level + 1,
            score = (INCREMENT_BY_SCORE * _uiStateData.value.level),
            currentActionSimonIndex = -1,
            currentActionPlayerIndex = _uiStateData.value.currentActionPlayerIndex + 1,

        )
        generateActions()
    }

    private fun generateActions() {
        val list = mutableListOf<Action>()
        _uiStateData.value = _uiStateData.value.copy(listActions = mutableListOf())
        for (i in 1.. _uiStateData.value.maxSteps){
            list.add(Action.values()[Random.nextInt(1, Action.values().count())])
        }
        _uiStateData.value = _uiStateData.value.copy(listActions = list)
    }


    private fun playAudio(mp: MediaPlayer) {
        println("AUDIO ${mp.isPlaying}")
        if (!mp.isPlaying) {
            mp.start()
        } else {
            mp.pause();
            mp.seekTo(0);
            mp.start()
        }
    }

    private fun pauseAudio(mp: MediaPlayer) {
        mp.pause();
        mp.seekTo(0);
    }
}
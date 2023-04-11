package com.example.simondice.form

import androidx.lifecycle.ViewModel
import com.example.simondice.domain.models.Action
import com.example.simondice.domain.models.Player
import kotlin.random.Random

class FormViewModel(): ViewModel() {
    private var INCREMENT_BY_LEVEL = 1
    private val INCREMENT_BY_SCORE = 10
    private var _maxSteps = 0
    private var _score = 0
    private var _level = 1
    private var _currentActionSimonIndex = -1
    private var _currentActionPlayerIndex = 0
    private var _listActions = mutableListOf<Action>()

    val score get() = this._score
    val level get() = this._level


    val started get() = _listActions.isNotEmpty()
    val endSpeak get() = _listActions.isNotEmpty() && _listActions.lastIndex < _currentActionSimonIndex
    val currentActionSimonIndex get() = _currentActionSimonIndex


    fun getCurrentAction(): Action {
        return if(_listActions.isNotEmpty() && _currentActionSimonIndex >= 0 && _currentActionSimonIndex <= _listActions.lastIndex)  _listActions[_currentActionSimonIndex] else Action.NO_ACTION
    }

    fun moveToNextAction(): Action {
        if(_listActions.isEmpty() || _currentActionSimonIndex > _listActions.lastIndex) return Action.NO_ACTION
        if(_currentActionSimonIndex == _listActions.lastIndex){
            _currentActionSimonIndex++
            return Action.NO_ACTION
        }
        _currentActionSimonIndex++
        return _listActions[_currentActionSimonIndex]

    }

    fun start() : FormViewModel {
        _score  = 0
        _level = 1
        _maxSteps = 0
        _currentActionPlayerIndex = 0
        _currentActionSimonIndex = 0
        generateActions()
        return this
    }

    fun end(mensaje: String): Player {
        val player = Player(mensaje,_score,_level)
        _score = 0
        _level = 1
        _maxSteps = 0
        INCREMENT_BY_LEVEL = 1
        _currentActionSimonIndex = -1
        _currentActionPlayerIndex = -1
        _listActions.clear()
        return player
    }

    fun validateAction(action: Action): Boolean{
        if(_listActions[_currentActionPlayerIndex] != action) return  false

        if(_listActions.lastIndex == _currentActionPlayerIndex){
            levelUp()
        }else{
            _currentActionPlayerIndex++
        }

        return true
    }

    private fun generateActions() {
        if (level == 5){
            INCREMENT_BY_LEVEL = 2
        }
        _maxSteps += INCREMENT_BY_LEVEL
        _listActions.clear()
        for (i in 1.. _maxSteps){
            _listActions.add(Action.values()[Random.nextInt(1, Action.values().count())])
        }
    }

    private fun levelUp(){
        _level++
        _score += (INCREMENT_BY_SCORE * _level)
        _currentActionSimonIndex = -1
        _currentActionPlayerIndex = 0
        generateActions()
    }
}
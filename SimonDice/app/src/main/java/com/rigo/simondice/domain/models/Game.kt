package com.rigo.simondice.domain.models

import kotlin.random.Random

class Game() {
    private val INCREMENT_BY_LEVEL = 2
    private val INCREMENT_BY_SCORE = 10
    private var _score = 0
    private var _level = 1
    private var _currentActionIndex = -1
    private var _currentActionPlayerIndex = 0
    private var _listActions = mutableListOf<Action>()

    val score get() = this._score
    val level get() = this._level

    val started get() = _listActions.isNotEmpty()
    val endSpeak get() = _listActions.isNotEmpty() && _listActions.lastIndex < _currentActionIndex
    val currentActionIndex get() = _currentActionIndex
    val currentActionPlayerIndex get() = _currentActionPlayerIndex
    val lastActionIndex get() = _listActions.lastIndex


    fun getCurrentAction(): Action{
        return if(_listActions.isNotEmpty() && _currentActionIndex >= 0 && _currentActionIndex <= _listActions.lastIndex)  _listActions[_currentActionIndex] else Action.NO_ACTION
    }

    fun moveToNextAction(): Action{
        if(_listActions.isEmpty() || _currentActionIndex > _listActions.lastIndex) return Action.NO_ACTION
        if(_currentActionIndex == _listActions.lastIndex){
            _currentActionIndex++
            return Action.NO_ACTION
        }
        _currentActionIndex++
        return _listActions[_currentActionIndex]

    }

    fun start() : Game{
        _score  = 0
        _level = 1
        _currentActionPlayerIndex = 0
        _currentActionIndex = 0
        generateActions()

        return this
    }

    fun end(namePlayer: String): Player{
        val player = Player(namePlayer,_score,_level)
        _score = 0
        _level = 1
        _currentActionIndex = -1
        _currentActionPlayerIndex = -1
        _listActions.clear()
        return player
    }

    fun validateAction(action: Action): Boolean{
        if(_listActions[_currentActionPlayerIndex] != action) return  false

        _currentActionPlayerIndex++

        if(_listActions.lastIndex == _currentActionPlayerIndex)
            newLevel()



        return true
    }



    private fun generateActions() {
        val maxActions = _level * INCREMENT_BY_LEVEL
        _listActions.clear()
        for (i in 0.. maxActions){
            _listActions.add(Action.values()[Random.nextInt(1, Action.values().count())])
        }
    }

    private fun newLevel(){
        _level++
        _score += (INCREMENT_BY_SCORE * _level)
        generateActions()
    }




}
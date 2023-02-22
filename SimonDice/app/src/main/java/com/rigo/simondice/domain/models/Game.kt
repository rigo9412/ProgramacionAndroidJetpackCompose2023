package com.rigo.simondice.domain.models

import kotlin.random.Random

class Game() {
    private val INCREMENT_BY_LEVEL = 4
    private val INCREMENT_BY_SCORE = 10

    private var _score = 0

    private var _level = 1
    private var _currentActionIndex = 0
    private var _listActions = mutableListOf<Actions>()

    val score get() = this._score
    val level get() = this._level

    val started get() = _listActions.isNotEmpty()
    val endSpeak get() = _listActions.isNotEmpty() && _listActions.lastIndex == _currentActionIndex
    val currentActionIndex get() = _currentActionIndex
    val lastActionIndex get() = _listActions.lastIndex


    fun getCurrentAction(): Actions?{
        return if(_listActions.isNotEmpty())  _listActions[_currentActionIndex] else null
    }

    fun moveToNextAction(): Actions?{
        if(_listActions.isEmpty()) return null
        if(_currentActionIndex == _listActions.lastIndex) return null

        _currentActionIndex++
        return _listActions[_currentActionIndex]

    }

    fun start() : Game{
        _score  = 0
        _level = 1
        generateActions()

        return this
    }

    fun end(namePlayer: String): Player{
        val player = Player(namePlayer,_score,_level)
        _score = 0
        _level = 1
        _currentActionIndex = 0
        _listActions.clear()
        return player
    }

    fun validateAction(index: Int, action: Actions): Boolean{
        if(_listActions[index] == action){
            _currentActionIndex++

            if(_listActions.lastIndex == index)
                newLevel()

        }

        return false
    }



    private fun generateActions() {
        val maxActions = _level * INCREMENT_BY_LEVEL
        _listActions.clear()
        for (i in 0.. maxActions){
            _listActions.add(Actions.values()[Random.nextInt(0, Actions.values().lastIndex)])
        }


    }

    private fun newLevel(){
        _score += (INCREMENT_BY_SCORE * INCREMENT_BY_LEVEL)
        _level++
        generateActions()
    }




}
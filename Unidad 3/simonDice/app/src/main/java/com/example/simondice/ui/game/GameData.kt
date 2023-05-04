package com.ejemplo.simondice.ui.game

import com.example.simondice.domain.models.getresponsetop.Action


data class GameData (
    val score: Int = 0,
    val level: Int = 1,
    val endGame: Boolean = false,
    val currentActionOn: Boolean = false,
    val actionPlayer: Action = Action.NO_ACTION,
    val maxSteps: Int = 1,
    val currentActionSimonIndex: Int = -1,
    val currentActionPlayerIndex: Int = 0,
    val listActions: List<Action> = mutableListOf()
)
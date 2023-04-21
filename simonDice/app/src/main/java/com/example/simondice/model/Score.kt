package com.example.simondice.model

data class Score(val player: String, val score: Int){
    val scoreList = listOf(
        Score("Jugador 1", 0),
        Score("Jugador 2", 0),
        Score("Jugador 3", 0),
        Score("Jugador 4", 0),
        Score("Jugador 5", 0),
        Score("Jugador 6", 0),
        Score("Jugador 7", 0),
        Score("Jugador 8", 0),
        Score("Jugador 9", 0),
        Score("Jugador 10", 0)
    )
}
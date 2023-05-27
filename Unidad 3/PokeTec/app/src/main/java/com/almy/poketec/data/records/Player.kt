package com.almy.poketec.data.records

import com.almy.poketec.data.listaPokemon
import com.almy.poketec.screens.pokedex.Pokemon

data class Player(
    var id: Int = 0,
    var name: String = "",
    var country: String = "",
    var time: Int = 0,
    var attemps: Int = 0,
    var score: Int = 0,
    val pokedex: List<Pokemon> = listaPokemon.map { it.copy() }
)

//lista de jugadores
var players: MutableList<Player> = mutableListOf()

//jugador seleccionado en el menu
var currentPlayer: Player? = null


package com.almy.poketec.data

data class Pokemon(
    val id: Int,
    val name: String,
    val type1: String,
    val type2: String,
    val total: Int,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val spAtk: Int,
    val speed: Int,
    val generation: Int,
    val legendary: String
)

var listaPokemon: List<Pokemon> = listOf()
package com.rigo9412.poketest.data.model

import com.google.gson.annotations.SerializedName

/*
*
*   "id": 1,
    "name": "Bulbasaur",
    "type1": "Grass",
    "type2": "Poison",
    "total": 318,
    "hp": 45,
    "attack": 49,
    "defense": 49,
    "spAtk": 65,
    "spDef": 65,
    "speed": 45,
    "generation": 1,
    "legendary": "False"
* */
data class PokemonEntity(
    @SerializedName("id")
    val numberID: Int,
    val name: String,
    val type1: String,
    val type2: String,
    val total: Int,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val spAtk: Int,
    val spDef: Int,
    val speed: Int,
    val generation: Int,
)

fun PokemonEntity.asExternalModel() = Pokemon(
    numberID,
    name,
    type1,
    type2,
    total,
    hp,
    attack,
    defense,
    spAtk,
    spDef,
    speed,
    generation,
)

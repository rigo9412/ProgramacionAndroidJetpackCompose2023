package com.tec.pokedexapp.data.model

import com.google.gson.annotations.SerializedName

data class PokemonEntity (
    @SerializedName("id")
    val id: Int,
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
    val legendary: Boolean
)

fun PokemonEntity.asExternalModel() = Pokemon(
    id,
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
    legendary
)
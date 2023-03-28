package com.lanazirot.pokedex.domain.models

import com.google.gson.annotations.SerializedName

data class PokemonMapped(
    val attack: Int,
    val defense: Int,
    val generation: Int,
    val hp: Int,
    @SerializedName("id")
    val id: Int,
    val legendary: String,
    val name: String,
    val spAtk: Int,
    val spDef: Int,
    val speed: Int,
    val total: Int,
    val type1: String,
    val type2: String
)
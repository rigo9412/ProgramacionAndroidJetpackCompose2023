package com.otop.chinpokomon.data.models

data class Pokemon (
    val id:Int,
    val name:String,
    val type1: String,
    val type2: String,
    val total: Int,
    val hp: Int,
    val attack: Int,
    val defense: Int,
    val spAtk: Int,
    val pDef: Int,
    val speed: Int,
    val generation: Int,
    val legendary: String
){
    fun PokemonIMG(): String {
        return "pokemons/${id.toString().padStart(3,'0')}.png";
    }
}
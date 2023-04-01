package com.tec.pokedexapp.data.model

data class Pokemon(
    val id : Int,
    val name : String,
    val type1 : String,
    val type2 : String,
    val total : Int,
    val hp : Int,
    val attack : Int,
    val defense : Int,
    val spAtk : Int,
    val spDef : Int,
    val speed : Int,
    val generation : Int,
    val legendary : Boolean,
    val discovered : Boolean = false
){
    fun getImagePath() : String{
        return "images/${String.format("%03d",id)}.png"
    }
}

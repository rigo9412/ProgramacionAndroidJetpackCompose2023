package com.tec.pokedexapp.data.model

data class Pokemon(
    val id : Int = 0,
    val name : String = "",
    val type1 : String = "",
    val type2 : String = "",
    val total : Int = 0,
    val hp : Int = 0,
    val attack : Int = 0,
    val defense : Int = 0,
    val spAtk : Int = 0,
    val spDef : Int = 0,
    val speed : Int = 0,
    val generation : Int = 0,
    val legendary : Boolean = false,
    var discovered : Boolean = false
){
    fun getImagePath() : String{
        return "images/${String.format("%03d",id)}.png"
    }
}

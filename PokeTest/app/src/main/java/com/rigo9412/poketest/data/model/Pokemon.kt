package com.rigo9412.poketest.data.model

data class Pokemon( val id: Int,
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
                    val generation: Int){


    fun getImagePath(): String {
        return "pokemons/${id.toString().padStart(3,'0')}.png"
    }
}

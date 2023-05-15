package com.tec.pokedexapp.data.model

data class ApiPostItem(
    val country: String,
    val finishDate: String,
    val minutesToFisnish: Int,
    val name: String,
    val startDate: String,
    val triesToFinish: Int
)
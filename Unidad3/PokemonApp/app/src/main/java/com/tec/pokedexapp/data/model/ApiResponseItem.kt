package com.tec.pokedexapp.data.model

data class ApiResponseItem(
    val country: String,
    val finishDate: String,
    val id: Int,
    val minutesToFinish: Int,
    val name: String,
    val startDate: String,
    val triesToFinish: Int
)
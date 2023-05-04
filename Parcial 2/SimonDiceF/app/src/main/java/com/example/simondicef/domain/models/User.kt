package com.example.simondicef.domain.models

data class User private constructor(
    val name: String,
    val score: Int,
    val id: Int
){
    companion object {
        operator fun invoke(
            name: String? = null,
            score: Int? = null,
            id: Int? = null
        ) = User(
            name ?: "-",
            score ?: 0,
            id ?: 0
        )
    }
}
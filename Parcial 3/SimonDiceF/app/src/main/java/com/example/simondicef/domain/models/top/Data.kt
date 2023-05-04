package com.example.simondicef.domain.models.top

data class Data(
    val level: Int,
    val name: String,
    val value: Int
)

data class postData(
    val data: Data
)
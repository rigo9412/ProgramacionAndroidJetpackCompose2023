package com.game.simondicevm.domain.models.getresponse

data class Attributes(
    val createdAt: String,
    val name: String?,
    val publishedAt: String,
    val updatedAt: String,
    val value: Int?
)
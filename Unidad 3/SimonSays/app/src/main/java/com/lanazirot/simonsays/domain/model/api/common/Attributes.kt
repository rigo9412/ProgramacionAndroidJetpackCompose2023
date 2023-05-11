package com.lanazirot.simonsays.domain.model.api.common

data class Attributes(
    val name: String?,
    val value: Int?,
    val createdAt: String,
    val updatedAt: String,
    val publishedAt: String,
    val level: Int
)
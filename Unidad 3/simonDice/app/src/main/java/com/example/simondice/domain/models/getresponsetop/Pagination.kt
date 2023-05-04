package com.example.simondice.domain.models.getresponsetop

data class Pagination (
    val page: Int,
    val pageCount: Int,
    val pageSize: Int,
    val total: Int
)
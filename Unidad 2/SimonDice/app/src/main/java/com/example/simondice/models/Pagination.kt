package com.example.simondice.models

data class Pagination(
    val page: Int,
    val pageCount: Int,
    val pageSize: Int,
    val total: Int
)
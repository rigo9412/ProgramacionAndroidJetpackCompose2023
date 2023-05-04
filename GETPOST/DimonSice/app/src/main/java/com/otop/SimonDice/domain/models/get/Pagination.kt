package com.otop.SimonDice.domain.models.get

data class Pagination (
    val page: Int,
    val pageCount: Int,
    val pageSize: Int,
    val total: Int
    )
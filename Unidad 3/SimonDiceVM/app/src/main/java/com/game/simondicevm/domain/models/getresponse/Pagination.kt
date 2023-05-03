package com.game.simondicevm.domain.models.getresponse

data class Pagination(
    val page: Int,
    val pageCount: Int,
    val pageSize: Int,
    val total: Int
)
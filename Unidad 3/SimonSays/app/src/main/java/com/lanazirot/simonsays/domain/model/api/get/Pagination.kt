package com.lanazirot.simonsays.domain.model.api.get

data class Pagination(
    val page: Int,
    val pageCount: Int,
    val pageSize: Int,
    val total: Int
)
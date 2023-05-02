package com.lanazirot.simonsays.domain.model.api.get

import com.lanazirot.simonsays.domain.model.api.common.Data

data class ApiResponse(
    val data: List<Data>,
    val meta: Meta
)
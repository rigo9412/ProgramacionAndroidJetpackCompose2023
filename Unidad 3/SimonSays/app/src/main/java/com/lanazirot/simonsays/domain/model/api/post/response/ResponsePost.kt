package com.lanazirot.simonsays.domain.model.api.post.response

import com.lanazirot.simonsays.domain.model.api.common.Data

data class ResponsePost(
    val `data`: Data,
    val meta: Meta
)
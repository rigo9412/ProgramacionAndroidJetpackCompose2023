package com.game.simondicevm.domain.models.getresponse

import com.game.simondicevm.domain.models.getresponse.Data
import com.game.simondicevm.domain.models.getresponse.Meta

data class TopResponse(
    val data: List<Data>,
    val meta: Meta
)
package com.almy.simondice.domain.models.getresponse

import com.almy.simondice.domain.models.getresponse.Data
import com.almy.simondice.domain.models.getresponse.Meta

data class TopResponse(
    val data: List<Data>,
    val meta: Meta
)
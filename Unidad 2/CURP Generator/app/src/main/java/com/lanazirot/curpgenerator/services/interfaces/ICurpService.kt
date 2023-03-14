package com.lanazirot.curpgenerator.services.interfaces

import com.lanazirot.curpgenerator.domain.models.Person

interface ICurpService {
    fun generarCurp(person: Person): String
}
package com.lanazirot.curpavanzado.services.interfaces

import com.lanazirot.curpgenerator.domain.models.Person

interface ICURPGenerator {
    fun generate(person: Person): String
}
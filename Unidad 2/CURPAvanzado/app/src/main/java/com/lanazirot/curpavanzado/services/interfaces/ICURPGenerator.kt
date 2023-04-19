package com.lanazirot.curpavanzado.services.interfaces

import com.lanazirot.curpavanzado.domain.models.Person

interface ICURPGenerator {
    fun generate(person: Person): String
}
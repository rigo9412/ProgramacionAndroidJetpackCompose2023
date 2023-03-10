package com.lanazirot.curpgenerator.domain.models

import com.lanazirot.curpgenerator.domain.enums.Gender
import com.lanazirot.curpgenerator.domain.enums.State

data class Person(
    var name: String = "",
    var lastname: String = "",
    var surname: String = "",
    val gender: Gender = Gender.MALE,
    var state: State = State.AGUASCALIENTES,
    var curp: String = "",
    var birthDate: String = ""
)
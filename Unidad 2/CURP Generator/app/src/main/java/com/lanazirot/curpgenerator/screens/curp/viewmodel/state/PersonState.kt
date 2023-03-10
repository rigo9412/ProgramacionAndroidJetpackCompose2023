package com.lanazirot.curpgenerator.screens.curp.viewmodel.state

import com.lanazirot.curpgenerator.domain.models.Person

data class PersonState(var person: Person = Person(), var isFormValid: Boolean = false)
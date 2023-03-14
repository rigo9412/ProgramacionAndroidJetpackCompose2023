package com.lanazirot.curpgenerator.services.impl

import com.lanazirot.curpgenerator.domain.models.Person
import com.lanazirot.curpgenerator.services.interfaces.ICurpService

class CurpService : ICurpService  {
    override fun generarCurp(state: Person): String {
        val digito0 = state.lastname.substring(0, 1)
        val digito1 = state.lastname.slice(1..2).replace(regex = Regex("[^A|E|I|O|U]"), replacement = "")[0]
        var digito2 = state.surname.substring(0, 1)
        var digito3 = state.name.substring(0, 1)
        var digito4 = state.birthDate.substring(2, 4)
        var digito5 = state.birthDate.substring(5, 7)
        var digito6 = state.birthDate.substring(8, 10)
        var digito7 = state.gender.code
        var digito8 = state.state.abbreviation
        var digito9 = state.lastname.slice(1..2).replace(regex = Regex("[A|E|I|O|U]"), replacement = "")
        var digito10 = state.surname.slice(1..2).replace(regex = Regex("[A|E|I|O|U]"), replacement = "")[0]
        var digito11 = state.name.slice(1..2).replace(regex = Regex("[A|E|I|O|U]"), replacement = "")
        var digito12 = if (state.birthDate.substring(0, 4).toInt() < 2000) (0..9).random() else ('A'..'Z').random().toString()
        var digito13 = (0..9).random()
        return "$digito0$digito1$digito2$digito3$digito4$digito5$digito6$digito7$digito8$digito9$digito10$digito11$digito12$digito13".replace("Ñ", "X").uppercase()
    }
}
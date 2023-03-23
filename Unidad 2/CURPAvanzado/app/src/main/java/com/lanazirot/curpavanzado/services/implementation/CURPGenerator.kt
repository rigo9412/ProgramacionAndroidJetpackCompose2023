package com.lanazirot.curpavanzado.services.implementation

import com.lanazirot.curpavanzado.common.utils.unaccent
import com.lanazirot.curpavanzado.domain.enums.blackListWords
import com.lanazirot.curpavanzado.services.interfaces.ICURPGenerator
import com.lanazirot.curpgenerator.domain.models.Person
import kotlin.math.abs

class CURPGenerator : ICURPGenerator {

    override fun generate(person: Person): String {
        var curp = ""
        curp += person.lastname.substring(0, 1)
        curp += person.lastname.slice(1..2).replace(regex = Regex("[^A|E|I|O|U]"), replacement = "")[0]
        curp += person.surname.substring(0, 1)
        curp += person.name.substring(0, 1)
        curp += person.birthDate.substring(2, 4)
        curp += person.birthDate.substring(5, 7)
        curp += person.birthDate.substring(8, 10)
        curp += person.gender.code
        curp += person.state.abbreviation
        curp += person.lastname.slice(1..2).replace(regex = Regex("[A|E|I|O|U]"), replacement = "")
        curp += person.surname.slice(1..2).replace(regex = Regex("[A|E|I|O|U]"), replacement = "")[0]
        curp += person.name.slice(1..2).replace(regex = Regex("[A|E|I|O|U]"), replacement = "")
        curp += if (person.birthDate.substring(0, 4).toInt() < 2000) "1" else "A"
        curp = checkBlackListCURP(curp.replace("Ñ", "X").uppercase())
        curp = curp.unaccent()
        curp += digitoVerificador(curp)
        return curp
    }

    private fun checkBlackListCURP(curp: String): String {
        return if (blackListWords.contains(curp.substring(0, 4))) curp.replaceRange(1, 2, "X") else curp
    }

    private fun digitoVerificador(curp: String): Int {
        val diccionario = "0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYZ"
        var suma = 0
        for (i in 0 until 17) {
            suma += diccionario.indexOf(curp[i]) * (18 - i)
        }
        val mod = suma % 10
        return if (mod == 0) 0 else abs(10 - mod)
    }

}
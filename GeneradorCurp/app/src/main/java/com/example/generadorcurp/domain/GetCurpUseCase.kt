package com.example.generadorcurp.domain

import com.example.generadorcurp.form.domain.*
import com.example.generadorcurp.ui.form.ui.CurpFormModelState
import java.time.LocalDate

class GetCurpUseCase {
    fun invoque(data: CurpFormModelState) : ResultCase{
        try{
            var curp = ""
            val inputData = data
            val nombre = normalizarNombre(inputData.nombre)
            val primerApellido = normalizarNombre(inputData.paterno)
            val segundoApellido = normalizarNombre(inputData.materno)
            val fechaNacimiento = LocalDate.parse(inputData.fechaNacimiento, FORMATTER_INPUT)

            curp += primerApellido[0]
            curp += getInternalVowel(primerApellido)
            curp += if (segundoApellido.isEmpty()) "X" else segundoApellido[0]
            curp += nombre[0]
            curp += fechaNacimiento.year.toString().substring(2, 4)
            curp += fechaNacimiento.monthValue.toString()
            curp += fechaNacimiento.dayOfMonth.toString()
            curp += inputData.genero.first
            curp += inputData.estado.first
            curp += getInternalConsonant(primerApellido)
            curp += if (segundoApellido.isEmpty()) "X" else getInternalConsonant(segundoApellido)
            curp += getInternalConsonant(nombre)
            curp += if (fechaNacimiento.year < 2000) '0' else 'A'
            curp += calcularUltimoDigitoCURP(curp)


            return  ResultCase.ResultSuccess(curp)
        }
        catch (e: java.lang.Exception){
            return ResultCase.ResultError(code = 500,error = "No se pudo generar el CURP")
        }

    }
}
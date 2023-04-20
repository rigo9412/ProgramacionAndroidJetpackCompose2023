package com.lanazirot.examen1.domain.services.implementation

import com.lanazirot.examen1.domain.services.interfaces.IGatoService

class GatoService : IGatoService{
    override fun determinarStatusJuego(board: Array<Array<String>>): String {
        var resultado: String
        var cadena = ""
        val gato = board

        for (fila in gato) {
            for (columna in fila) {
                cadena += columna
            }
            resultado = verificarGanadorSegunCadena(cadena)
            if (resultado != "") return imprimirResultado(resultado)
            cadena = ""
        }
        for (columna in 0..2) {
            for (fila in 0..2) {
                cadena += gato[fila][columna]
            }
            resultado = verificarGanadorSegunCadena(cadena)
            if (resultado != "") return imprimirResultado(resultado)
            cadena = ""
        }
        for (fila in 0..2) {
            cadena += gato[fila][fila]
        }
        resultado = verificarGanadorSegunCadena(cadena)
        if (resultado != "") return imprimirResultado(resultado)
        cadena = ""
        for (fila in 0..2) {
            cadena += gato[fila][2 - fila]
        }
        resultado = verificarGanadorSegunCadena(cadena)
        if (resultado != "") return imprimirResultado(resultado)
        for (fila in gato) {
            for (columna in fila) {
                if (columna == "") {
                    return ""
                }
            }
        }
        return "empate"
    }

    private fun verificarGanadorSegunCadena(cadena: String): String = when (cadena) {
        "XXX" -> "X"
        "OOO" -> "O"
        else -> ""
    }

    private fun imprimirResultado(ganador: String): String = "Ganador: $ganador"
}
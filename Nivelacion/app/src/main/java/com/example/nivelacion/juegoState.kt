package com.example.nivelacion

data class juegoState(var numero:Int = 1, var suma: Int=0,var iniciar:Boolean=true,
                      var list: MutableList<Int> = mutableListOf(), var listaMostrar:MutableList<Informacion> = mutableListOf(),
                        var contador:Int = 1, var LimiteN:String="")

sealed class vistas{
    object Init: vistas()
    object Juego: vistas()
    object Resultados: vistas()
    object ResultadoCorrecto: vistas()
    object ResultadoIncorrecto: vistas()
    object MostrarResultado: vistas()
}

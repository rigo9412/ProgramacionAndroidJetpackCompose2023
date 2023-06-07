package com.program.nivelacion.ui.data

import android.graphics.Color

data class UiStateData(
    var ListaNumero0_9:List<Int> = mutableListOf(
        -9,-8,-7,-6,-5,-4,-3,-2,-1,1,2,3,4,5,6,7,8,9),
    var Numero0_9: Int = 0,
    var Nveces: String = "",
    var ListaNveces:List<Int> = mutableListOf(
        0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30
        ,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50
        ,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70
        ,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90
        ,91,92,93,94,95,96,97,98,99,100),
    var Resultado: Int = 0,

    var CadenaResultado : String = "",
    var ColorFondo: Int = Color.WHITE,
    var Mensaje: String = "",
    var ValorN : String = "",
    var LISTARESULTADOS : MutableList<Resultados> = mutableListOf()
)

sealed class UiState{
    object Init: UiState()
    object pantallaJuego: UiState()
    object pantallaResultado: UiState()
    object pantallaMostrarLista: UiState()
}
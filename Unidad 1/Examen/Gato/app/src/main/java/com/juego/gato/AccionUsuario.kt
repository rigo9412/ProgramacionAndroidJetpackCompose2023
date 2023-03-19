package com.juego.gato

sealed class AccionUsuario {
    object BotonReiniciarCLicked: AccionUsuario()
    data class TableroPad(val cellNo: Int): AccionUsuario()
}

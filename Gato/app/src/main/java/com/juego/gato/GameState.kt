package com.juego.gato

data class GameState(
    val jugadorCirculoPuntuacion: Int = 0,
    val jugadorEquisPuntuacion: Int = 0,
    val dibujoCount: Int = 0,
    val descripcionTexto: String = "Player 'O' turn",
    val turnoActual: ValorCeldaJugador = ValorCeldaJugador.CIRCULO,
    val tipoVictoria: TipoVictoria = TipoVictoria.NONE,
    val aGanado: Boolean = false
)

enum class ValorCeldaJugador {
    CIRCULO,
    EQUIS,
    NO_ACTION
}

enum class TipoVictoria {
    HORIZONTAL1,
    HORIZONTAL2,
    HORIZONTAL3,
    VERTICAL1,
    VERTICAL2,
    VERTICAL3,
    DIAGONAL1,
    DIAGONAL2,
    NONE
}
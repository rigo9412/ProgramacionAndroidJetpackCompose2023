package com.lanazirot.examen1.domain.services.interfaces

interface IGatoService {
    fun determinarStatusJuego(board: Array<Array<String>>): String
}
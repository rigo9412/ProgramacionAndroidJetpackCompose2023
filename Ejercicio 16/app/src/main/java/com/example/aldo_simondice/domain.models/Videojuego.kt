package com.example.aldo_simondice.domain.models

import kotlin.random.Random

class Videojuevo() {
    private val INCREMENTO_NIVEL = 1
    private val INCREMENTO_SCORE = 10
    private var _maxPasos = 1
    private var _score = 0
    private var _nivel = 1
    private var _actualAccionSimonIndex = -1
    private var _actualAccionJugadorIndex = 0
    private var _listaAcciones = mutableListOf<Accion>()

    //Propiedades para leer los valores privados de la clase
    val score get() = this._score
    val nivel get() = this._nivel
    val actualAccionJugadorIndex get() = _actualAccionJugadorIndex
    val actualAccionSimonIndex get() = _actualAccionSimonIndex
    val ultimaAccionIndex get() = _listaAcciones.lastIndex

    val comienzo get() = _listaAcciones.isNotEmpty()
    val endSpeak get() = _listaAcciones.isNotEmpty() && _listaAcciones.lastIndex < _actualAccionSimonIndex

    //METODOS
    fun obtenerActualAccion(): Accion{
        return if(_listaAcciones.isNotEmpty() && _actualAccionSimonIndex >= 0 && _actualAccionSimonIndex <= _listaAcciones.lastIndex)
            _listaAcciones[_actualAccionSimonIndex] else Accion.NO_ACCION
    }

    fun moverSiguienteAccion(): Accion{
        if(_listaAcciones.isEmpty() || _actualAccionSimonIndex > _listaAcciones.lastIndex ) return Accion.NO_ACCION
        if(_actualAccionSimonIndex == _listaAcciones.lastIndex){
            _actualAccionSimonIndex++
            return Accion.NO_ACCION
        }
        _actualAccionSimonIndex++
        return _listaAcciones[_actualAccionSimonIndex]
    }

    fun empezar(): Videojuevo{
        _score = 0
        _nivel = 1
        _actualAccionJugadorIndex = 0
        _actualAccionSimonIndex = 0
        generarAcciones()
        return this
    }

    fun final(nombreJugador: String) : Jugador{
        val jugador = Jugador(nombreJugador,_score,_nivel)
        _score = 0
        _nivel = 1
        _maxPasos = 1
        _actualAccionSimonIndex = -1
        _actualAccionJugadorIndex = -1
        _listaAcciones.clear()
        return jugador
    }

    fun validarAccion(action: Accion): Boolean{
        if(_listaAcciones[_actualAccionJugadorIndex] != action) return false
        if(_listaAcciones.lastIndex == _actualAccionJugadorIndex){
            aumentarNivel()
        }
        return true
    }

    private fun generarAcciones(){
        _listaAcciones.clear()
        for(i in 1.. _maxPasos){
            _listaAcciones.add(Accion.values()[Random.nextInt(1, Accion.values().count())])
        }
    }
    private fun aumentarNivel(){
        _nivel++
        _score += (INCREMENTO_SCORE * _nivel )
        _actualAccionSimonIndex = -1
        _actualAccionJugadorIndex = 0
        generarAcciones()
    }
}
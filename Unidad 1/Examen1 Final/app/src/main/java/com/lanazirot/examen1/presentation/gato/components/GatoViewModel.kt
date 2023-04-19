package com.lanazirot.examen1.presentation.gato.components

import androidx.lifecycle.ViewModel
import com.lanazirot.examen1.domain.services.interfaces.IGatoService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class GatoViewModel @Inject constructor(private val gatoService: IGatoService) : ViewModel() {

    private val _gato = MutableStateFlow(GatoState())
    val gato = _gato.asStateFlow()

    init {
        _gato.value = GatoState(
            gatoArray = _gato.value.gatoArray,
            isDraw = false,
            player = "X",
            winner = null,
            isGameOver = false
        )
    }


    fun colocarValorJugadorAGatoArray(fila: Int, columna: Int) {
        _gato.value = GatoState(
            gatoArray = _gato.value.gatoArray.apply { this[fila][columna] = _gato.value.player },
            isDraw = false,
            player = if (_gato.value.player == "X") "O" else "X",
            winner = null,
            isGameOver = false
        )
    }

    fun resetGame() {
        _gato.value = GatoState(
            gatoArray = arrayOf(arrayOf("", "", ""), arrayOf("", "", ""), arrayOf("", "", "")),
            isDraw = false,
            player = "X",
            winner = null,
            isGameOver = false
        )
    }


    fun determinarStatusJuego(): String {
        return gatoService.determinarStatusJuego(_gato.value.gatoArray)
    }
}
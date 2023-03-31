package com.game.guesspoke.screens.game

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.almy.poketec.data.Pokemon
import com.almy.poketec.data.listaPokemon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.*
import kotlin.concurrent.timerTask

class GameViewModel : ViewModel() {
    //variable que indica el estado de los datos
    private val _uiStateData = MutableStateFlow<GameUiState>(GameUiState())
    val uiStateData: StateFlow<GameUiState> = _uiStateData

    //variable que indica el estado de la interfaz
    private val _uiState = MutableStateFlow<ScreenUiState>(ScreenUiState.Init)
    val uiState: StateFlow<ScreenUiState> = _uiState

    //constructor
    init {
        loadPokemon()
    }

    fun loadPokemon() {
        _uiState.value = ScreenUiState.CargarPokemon
        if (listaPokedex == null) {
            pickearPrimerPokemonCorrecto()
            pickearPrimerosCuatroPokemons()
        } else {
            pickearPokemonCorrecto()
            pickearCuatroPokemons()
        }
        _uiState.value = ScreenUiState.MostrarPokemon(
            id = _uiStateData.value.pokemonActual.id,
            cuatroPokemons = _uiStateData.value.cuatroPokemonsDesordenado,
            vida = _uiStateData.value.vidas,
            pts = _uiStateData.value.puntos
        )
    }

    //metodo para pickear el pokemon correcto (osea la respuesta correcta xd)
    //nota: los metodos que dicen "primer" es solo para el caso de cuando las listas/objetos estan vacios, osea solo cuando recien empieza el juego
    private fun pickearPrimerPokemonCorrecto() {
        _uiStateData.value = _uiStateData.value.copy(pokemonActual = listaPokemon.random())
    }

    private fun pickearPokemonCorrecto() {
        var pokemon: Pokemon
        do {
            pokemon = listaPokemon.random()
        } while (listaPokedex.contains(pokemon))

        var vidas = _uiStateData.value.vidas
        if (vidas == 0) {
            vidas = 3
        }

        _uiStateData.value =
            _uiStateData.value.copy(pokemonActual = pokemon, esCorrecto = false, vidas = vidas)
    }

    //metodo para agarrar 4 pokemon, osea las 4 posibles respuestas
    private fun pickearPrimerosCuatroPokemons() {
        var cuatroPokemon: MutableList<Pokemon> = mutableListOf(_uiStateData.value.pokemonActual)
        var i = 3
        while (i != 0) {
            var pokemon: Pokemon = listaPokemon.random()
            if (cuatroPokemon.contains(pokemon)) {
                continue
            } else {
                cuatroPokemon.add(pokemon)
                i--
            }
        }
        var cuatroPokemonsDesordenado = cuatroPokemon
        cuatroPokemonsDesordenado.shuffle()
        _uiStateData.value = _uiStateData.value.copy(
            cuatroPokemons = cuatroPokemon,
            cuatroPokemonsDesordenado = cuatroPokemonsDesordenado
        )
    }

    private fun pickearCuatroPokemons() {
        var cuatroPokemon: MutableList<Pokemon> = mutableListOf(_uiStateData.value.pokemonActual)
        var i = 3
        while (i != 0) {
            var pokemon: Pokemon
            do {
                pokemon = listaPokemon.random()
            } while (listaPokedex.contains(pokemon))

            if (cuatroPokemon.contains(pokemon)) {
                continue
            } else {
                cuatroPokemon.add(pokemon)
                i--
            }
        }
        var cuatroPokemonsDesordenado = cuatroPokemon
        cuatroPokemonsDesordenado.shuffle()
        _uiStateData.value = _uiStateData.value.copy(
            cuatroPokemons = cuatroPokemon,
            cuatroPokemonsDesordenado = cuatroPokemonsDesordenado
        )
    }

    //para guardar la opcion que elige el usuario
    fun opcionElegida(opcion: Int) {
        _uiStateData.value = _uiStateData.value.copy(respuestaElegida = opcion)
    }

    //evalua la respuesta del usuario, se usa parametro para saber si respondio a tiempo o no
    fun Evaluar(seAgotoElTiempo: Boolean) {
        _uiState.value = ScreenUiState.Evaluar(
            opcionElegida = _uiStateData.value.respuestaElegida,
            idOpcionCorrecta = _uiStateData.value.pokemonActual.id
        )

        var listaBln: MutableList<Boolean> = _uiStateData.value.seAgotoElTiempo
        listaBln.add(seAgotoElTiempo)

        //con "n" guardamos la respuesta elegida del usuario
        var n = _uiStateData.value.respuestaElegida

        //si si se agotó el tiempo entonces hay que asegurar una respuesta incorrecta
        if (seAgotoElTiempo) {
            while (_uiStateData.value.pokemonActual == _uiStateData.value.cuatroPokemonsDesordenado[n]) {
                n++
            }
        }

        //guardemos los pts y las vidas para poder actualzuar estos valores
        var pts = _uiStateData.value.puntos
        var vida = _uiStateData.value.vidas

        //aqui mero evalua si es correcto o no (recordar que "pokemonActual" es la respuesta correcta)
        if (_uiStateData.value.cuatroPokemonsDesordenado[n] == _uiStateData.value.pokemonActual) {
            _uiStateData.value = _uiStateData.value.copy(
                esCorrecto = true,
                puntos = pts + 1,
                seAgotoElTiempo = listaBln
            )
            //registrarlo en la pokedex
            listaPokedex.add(_uiStateData.value.pokemonActual)

        } else {
            _uiStateData.value = _uiStateData.value.copy(
                esCorrecto = false,
                vidas = vida - 1,
                seAgotoElTiempo = listaBln
            )
        }

        AgregarPreguntasYRespuestas(
            _uiStateData.value.cuatroPokemonsDesordenado,
            _uiStateData.value.pokemonActual,
            _uiStateData.value.respuestaElegida
        )

        _uiState.value = ScreenUiState.Resultado(esCorrecto = _uiStateData.value.esCorrecto)

        Timer().schedule(
            timerTask {
                if (_uiStateData.value.esCorrecto) {
                    if (listaPokedex.size == 151) {
                        _uiState.value = ScreenUiState.PokedexCompletada
                    } else {
                        //_uiState.value = ScreenUiState.CargarPokemon
                        loadPokemon()
                    }
                } else {
                    if (_uiStateData.value.vidas == 0) {
                        _uiState.value = ScreenUiState.JuegoTerminado(
                            _uiStateData.value.puntos,
                            _uiStateData.value.listaPreguntas,
                            _uiStateData.value.listaPokemonCorrecto,
                            _uiStateData.value.listaRespuestaElegida,
                            _uiStateData.value.seAgotoElTiempo
                        )
                        var intentos = _uiStateData.value.intentos + 1
                        _uiStateData.value =
                            _uiStateData.value.copy(
                                puntos = 0,
                                intentos = intentos,
                                listaPreguntas = mutableListOf(),
                                listaPokemonCorrecto = mutableListOf(),
                                listaRespuestaElegida = mutableListOf(),
                                seAgotoElTiempo = mutableListOf()
                            )
                    } else {
                        loadPokemon()
                    }
                }
            }, 1500
        )
    }

    fun AgregarPreguntasYRespuestas(poke: List<Pokemon>, pokeCorrecto: Pokemon, respuesta: Int) {
        //obtenemos la lista actual
        var listaPreguntas: MutableList<Quadruple<Pokemon, Pokemon, Pokemon, Pokemon>> =
            _uiStateData.value.listaPreguntas
        var listaPokemonCorrecto: MutableList<Pokemon> = _uiStateData.value.listaPokemonCorrecto
        var listaRespuestaElegida: MutableList<Int> = _uiStateData.value.listaRespuestaElegida

        //agregamos la ultima pregunta
        listaPreguntas.add(Quadruple(poke[0], poke[1], poke[2], poke[3]))
        listaPokemonCorrecto.add(pokeCorrecto)
        listaRespuestaElegida.add(respuesta)

        //actualizamos el listado
        _uiStateData.value = _uiStateData.value.copy(
            listaPreguntas = listaPreguntas,
            listaPokemonCorrecto = listaPokemonCorrecto,
            listaRespuestaElegida = listaRespuestaElegida
        )
    }
}


package com.game.guesspoke.screens.game


import com.almy.poketec.data.Pokemon
import com.almy.poketec.data.listaPokemon
import java.io.Serializable

data class GameUiState(
    val pokemonActual: Pokemon = listaPokemon[0],
    val vidas: Int = 3,
    val puntos: Int = 0,
    val esCorrecto: Boolean = false,
    val respuestaElegida: Int = 0,
    val juegoTerminado: Boolean = false,
    val cuatroPokemons: List<Pokemon> = listOf(),
    val cuatroPokemonsDesordenado: List<Pokemon> = listOf(),
    val listaPreguntas: MutableList<Quadruple<Pokemon, Pokemon, Pokemon, Pokemon>> = mutableListOf(),
    val listaPokemonCorrecto: MutableList<Pokemon> = mutableListOf(),
    val listaRespuestas: MutableList< Quadruple<Int, Int, Int, Int> > = mutableListOf(),
    val listaRespuestaElegida: MutableList<Int> = mutableListOf(),
    val intentos: Int = 0,
    val seAgotoElTiempo: MutableList<Boolean> = mutableListOf()
)

sealed class ScreenUiState {
    object Init : ScreenUiState()
    object CargarPokemon: ScreenUiState()
    class MostrarPokemon(
        val id: Int, val cuatroPokemons: List<Pokemon>, val vida: Int, val pts: Int
    ) : ScreenUiState()
    class Evaluar(val opcionElegida: Int, val idOpcionCorrecta: Int) : ScreenUiState()
    class Resultado(val esCorrecto: Boolean) : ScreenUiState()
    class JuegoTerminado(
        val puntos: Int,
        val listaPreguntas: MutableList<Quadruple<Pokemon, Pokemon, Pokemon, Pokemon>>,
        val listaPokemonCorrecto: MutableList<Pokemon>,
        val listaRespuestaElegida: MutableList<Int>,
        val seAgotoElTiempo: MutableList<Boolean>
    ) : ScreenUiState()
    object PokedexCompletada : ScreenUiState()
}

data class Quadruple<A,B,C,D>(var first: A, var second: B, var third: C, var fourth: D):
    Serializable {
    override fun toString(): String = "($first, $second, $third, $fourth)"
}

var listaPokedex: MutableList<Pokemon> = mutableListOf()

data class Preguntas(
    var id: Int = 0,
    var esCorrecto: Boolean = false,
    var pokemones: MutableList<Pokemon> = mutableListOf(),
    var opcionElegida: Int = 0,
    var opcionCorrecta: Int = 0,
    var seAgotoElTiempo: Boolean = false
)
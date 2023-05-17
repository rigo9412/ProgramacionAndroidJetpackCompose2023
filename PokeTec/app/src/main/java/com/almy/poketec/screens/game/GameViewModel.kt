package com.almy.poketec.screens.game

import androidx.lifecycle.ViewModel
import com.almy.poketec.data.listaPokemon
import com.almy.poketec.data.records.Player
import com.almy.poketec.data.records.currentPlayer
import com.almy.poketec.data.records.players
import com.almy.poketec.screens.pokedex.Pokemon
import com.game.guesspoke.screens.game.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
        //loadPokemon()
        Inicio()
    }

    fun Inicio()
    {
        _uiState.value = ScreenUiState.Inicio
    }

    fun CargarEntrenadores()
    {
        _uiState.value = ScreenUiState.SeleccionarJugador(players)
    }

    fun CrearNuevoEntrenador()
    {
        var player = Player()
        var n = 0
        players.forEach {
            if (it.name.contains("Slot"))
            {
                n++
            }
        }
        n++

        player.id = players.size + 1
        player.name = "Slot $n"
        //player.pokedex = listaPokemon as MutableList<Pokemon>
        player.pokedex.forEach {
            if(it.id < 150)
            {
                it.discover = true
            }
        }
        players.add(player)

        currentPlayer = player
        _uiState.value = ScreenUiState.Jugar
    }

    fun SeleccionarEntrenador(id: Int)
    {
        currentPlayer = players[id-1]
        _uiState.value = ScreenUiState.Jugar
    }

    fun MostrarTop()
    {
        _uiState.value = ScreenUiState.TopMaestrosPokemon(players)
    }

    fun Jugar()
    {
        _uiState.value = ScreenUiState.Jugar
    }

    fun CargandoPokemones()
    {
        _uiState.value = ScreenUiState.CargarPokemon
        Timer().schedule(timerTask {
            loadPokemon()
        }, 3000)
    }

    fun loadPokemon() {
        _uiState.value = ScreenUiState.CargarPokemon

        //primero llenar la pokedex de acuerdo a como la tiene el jugador
        listaPokedex = (currentPlayer?.pokedex as MutableList<Pokemon>?)!!

        //comprobar si son 0 o cuantos lleva
        var n = 0
        listaPokedex.forEach {
            if(it.discover == true)
            {
                n++
            }
        }

        //checar si no hay pokemon descubiertos
        //if (listaPokedex == null) {
        if(n == 0)
        {
            pickearPrimerPokemonCorrecto()
            pickearPrimerosCuatroPokemons()
        } else if(n == 151){
            _uiState.value = ScreenUiState.PokedexCompletada
            return
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
        } while ( (listaPokedex.find{ it.id == pokemon.id})?.discover  == true )//listaPokedex.contains(pokemon))

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

        //hay que comprobar si ya llego a 148 la lista, entonces vamos a mostrar menos de 4 respuestas
        var n = 0
        listaPokedex.forEach {
            if(it.discover == true)
            {
                n++
            }
        }
        var i = if (n == 148) 2
        else if (n == 149) 1 else if(n == 150) 0 else 3

        while (i != 0) {
            var pokemon: Pokemon
            do {
                pokemon = listaPokemon.random()
            } while ((listaPokedex.find{ it.id == pokemon.id})?.discover  == true )

            //no va a permitir respuestas repetidas
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
    fun Evaluar(seAgotoElTiempo: Boolean, tiempo: Int) {
        /*_uiState.value = ScreenUiState.Evaluar(
            opcionElegida = _uiStateData.value.respuestaElegida,
            idOpcionCorrecta = _uiStateData.value.pokemonActual.id
        )*/

        //guardar el tiempo que se tardo el jugador
        currentPlayer?.time = currentPlayer?.time?.plus(tiempo)!!
        players.forEachIndexed{ index, player ->
            if(player.id == currentPlayer!!.id)
            {
                players[index] = currentPlayer!!
            }
        }

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
            var poke: Pokemon = _uiStateData.value.pokemonActual
            poke.discover = true
            _uiStateData.value = _uiStateData.value.copy(
                pokemonActual = poke,
                esCorrecto = true,
                puntos = pts + 1,
                seAgotoElTiempo = listaBln,
            )

            //registrarlo en la pokedex
            //listaPokedex.add(_uiStateData.value.pokemonActual)
            currentPlayer?.pokedex?.forEach {
                if(it.id == _uiStateData.value.pokemonActual.id)
                {
                    it.discover = true
                }
            }

            players.forEachIndexed { index, player->
                if(player.id == currentPlayer?.id)
                {
                    players[index] = currentPlayer!!
                }
            }

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
                    //if (listaPokedex.size == 151) {
                    var size = 0
                    currentPlayer?.pokedex?.forEach {
                        if(it.discover == true)
                        {
                            size++
                        }
                    }
                    if(size == 151)
                    {
                        _uiState.value = ScreenUiState.DatosMaestroPokemon
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

                        //registrar puntuacion
                        listaPuntuaciones.add(_uiStateData.value.puntos)

                        //sumar intentos
                        totalDeIntentos.plus(1)
                        var intentos = _uiStateData.value.intentos + 1

                        //guardar intentos del jugador
                        currentPlayer?.attemps = currentPlayer?.attemps!! + 1
                        players.forEachIndexed{index, player ->
                            if(player.id == currentPlayer!!.id)
                            {
                                players[index] = currentPlayer!!
                            }
                        }

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
        //hay que saber cuantos pokemon hay
        var n = poke.size

        //obtenemos la lista actual
        var listaPreguntas: MutableList<Quadruple<Pokemon, Pokemon, Pokemon, Pokemon>> =
            _uiStateData.value.listaPreguntas
        var listaPokemonCorrecto: MutableList<Pokemon> = _uiStateData.value.listaPokemonCorrecto
        var listaRespuestaElegida: MutableList<Int> = _uiStateData.value.listaRespuestaElegida

        //agregamos la ultima pregunta
        if(n == 3)
        {
            listaPreguntas.add(Quadruple(poke[0], poke[1], poke[2], Pokemon()))
        } else if (n == 2)
        {
            listaPreguntas.add(Quadruple(poke[0], poke[1], Pokemon(), Pokemon()))
        } else if(n == 1){
            listaPreguntas.add(Quadruple(poke[0], Pokemon(), Pokemon(), Pokemon()))
        } else{
            listaPreguntas.add(Quadruple(poke[0], poke[1], poke[2], poke[3]))
        }

        listaPokemonCorrecto.add(pokeCorrecto)
        listaRespuestaElegida.add(respuesta)

        //actualizamos el listado
        _uiStateData.value = _uiStateData.value.copy(
            listaPreguntas = listaPreguntas,
            listaPokemonCorrecto = listaPokemonCorrecto,
            listaRespuestaElegida = listaRespuestaElegida
        )
    }

    fun GuardarDatosEntrenador(
        name: String,
        country: String
    ){
        currentPlayer?.name = name
        currentPlayer?.country = country

        var n = 0
        players.forEach {
            if (it.id == currentPlayer?.id)
            {
                players[n] = currentPlayer!!
            }
            n++
        }
        _uiState.value = ScreenUiState.PokedexCompletada
    }
}


package com.almy.poketec.screens.game

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.CountDownTimer
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.almy.poketec.CalculateScore
import com.almy.poketec.CreatePlayers
import com.almy.poketec.R
import com.almy.poketec.countrys.countryList
import com.almy.poketec.data.listaPokemon
import com.almy.poketec.data.records.Player
import com.almy.poketec.data.records.currentPlayer
import com.almy.poketec.data.records.players
import com.almy.poketec.screens.pokedex.Pokemon
import com.almy.poketec.screens.pokedexCompleted.FormScreenPokedexCompleted
import com.almy.poketec.screens.pokedexCompleted.ImageFondo
import com.almy.poketec.ui.theme.*
import com.game.guesspoke.screens.game.*

@Composable
fun GameScreen1(viewModel: GameViewModel, darkMode: MutableState<Boolean>) {
    val state = viewModel.uiState.collectAsState().value
    when (state) {
        is ScreenUiState.Inicio -> {
            HomeScreen(viewModel = viewModel, darkMode)
        }
        is ScreenUiState.TopMaestrosPokemon -> {
            ScreenReady(top1 = players, viewModel = viewModel)
        }
        is ScreenUiState.SeleccionarJugador -> {
            SelectTrainer(viewModel = viewModel, players = players)
        }
        is ScreenUiState.Jugar -> {
            PlayScreen(viewModel = viewModel)
        }
        is ScreenUiState.CargarPokemon -> {
            Cargando()
        }
        is ScreenUiState.MostrarPokemon -> {
            MostrarPokemonScreen(gameViewModel = viewModel)
            /*Timer().schedule(timerTask {
                if (state is ScreenUiState.MostrarPokemon)
                {
                    viewModel.Evaluar(true)
                }
            }, 5000)*/
        }
        is ScreenUiState.Evaluar -> {}
        is ScreenUiState.Resultado -> ResultadoScreen(gameViewModel = viewModel)
        is ScreenUiState.JuegoTerminado -> ScreenJuegoTerminado(
            gameViewModel = viewModel,
            state.puntos,
            state.listaPreguntas,
            state.listaPokemonCorrecto,
            state.listaRespuestaElegida,
            state.seAgotoElTiempo
        )
        is ScreenUiState.PokedexCompletada -> {
            FormScreenPokedexCompleted(viewModel)
        }
        is ScreenUiState.DatosMaestroPokemon -> {
            PedirDatosDelEntrenador(viewModel = viewModel)
        }
        is ScreenUiState.MostrarDatos -> { FinalScoreDialog(viewModel = viewModel, player = state.player)}
        else -> {

        }
    }
}

@Composable
fun HomeScreen(
    viewModel: GameViewModel,
    darkMode: MutableState<Boolean>
) {
    if (players.isEmpty()) {
        CreatePlayers()
    }
    val fondo = if (darkMode.value) {
        "fondoNoche.jpg"

    } else {
        "fondoDia.jpg"
    }
    //ImageFondo("fondoDia.jpg", true)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        ImageFondo(fondo, true)
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "PokeTec ;)",
            fontSize = 40.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(bottom = 10.dp)
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "Hola :D ¿Qué deseas hacer?",
            fontSize = 20.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(bottom = 25.dp),
            textAlign = TextAlign.Center
        )
        Button(
            onClick = { viewModel.CargarEntrenadores() },
            modifier = Modifier
                .padding(bottom = 15.dp)
                .width(300.dp)
                .height(70.dp)
                .clip(RoundedCornerShape(20.dp))
        ) {
            Text("Seleccionar entrenador", fontSize = 20.sp, fontWeight = FontWeight.ExtraLight)
        }
        Button(
            onClick = { viewModel.MostrarTop() },
            modifier = Modifier
                .width(300.dp)
                .height(70.dp)
                .clip(RoundedCornerShape(20.dp))
        ) {
            Text("Top Maestros Pokemon", fontSize = 20.sp, fontWeight = FontWeight.ExtraLight)
        }
    }
}

@Composable
fun SelectTrainer(
    viewModel: GameViewModel,
    players: List<Player>
) {
    ImageFondo("fondoAtardecer.jpg", true)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Selecciona un entrenador",
            fontSize = 20.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(bottom = 25.dp),
            textAlign = TextAlign.Center
        )
        Button(
            onClick = { viewModel.Inicio() },
            modifier = Modifier
                .padding(bottom = 15.dp)
                .width(120.dp)
                .height(35.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Purple700)
        ) {
            Text(
                "Regresar",
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraLight,
                color = Color.White
            )
        }
        Button(
            onClick = { viewModel.CrearNuevoEntrenador() },
            modifier = Modifier
                .padding(bottom = 15.dp)
                .width(250.dp)
                .height(60.dp)
                .clip(RoundedCornerShape(20.dp)),
            colors = ButtonDefaults.buttonColors(backgroundColor = Purple500)
        ) {
            Text(
                "Nueva partida",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraLight,
                color = Color.White
            )
        }
        players.forEach {
            Button(
                onClick = { viewModel.SeleccionarEntrenador(it.id) },
                modifier = Modifier
                    .padding(bottom = 15.dp)
                    .width(250.dp)
                    .height(60.dp)
                    .clip(RoundedCornerShape(20.dp))
            ) {
                Text(it.name, fontSize = 20.sp, fontWeight = FontWeight.ExtraLight)
            }
        }
    }
}

@Composable
fun PlayScreen(
    viewModel: GameViewModel
) {
    ImageFondo("fondoAtardecer.jpg", true)
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "¡Bienvenido de vuelta, ${currentPlayer?.name}!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(bottom = 15.dp),
            textAlign = TextAlign.Center
        )
        Button(
            onClick = { viewModel.CargarEntrenadores() },
            modifier = Modifier
                .padding(bottom = 35.dp)
                .width(120.dp)
                .height(35.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Purple700)
        ) {
            Text(
                "Regresar",
                fontSize = 15.sp,
                fontWeight = FontWeight.ExtraLight,
                color = Color.White
            )
        }
        Text(
            "¡Adivina todos los pokemones para completar la pokedex!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Light,
            modifier = Modifier.padding(bottom = 25.dp),
            textAlign = TextAlign.Center
        )
        Button(
            onClick = { viewModel.CargandoPokemones() },
            modifier = Modifier
                .width(150.dp)
                .height(80.dp)
                .clip(RoundedCornerShape(20.dp))
        ) {
            Text("Jugar", fontSize = 25.sp, fontWeight = FontWeight.ExtraLight)
        }
    }
}

@Composable
fun Cargando() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Cargando pokemones... Por favor espere :)",
            fontSize = 20.sp,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun MostrarPokemonScreen(
    gameViewModel: GameViewModel
) {
    val data = gameViewModel.uiStateData.collectAsState().value
    val millisInFuture = 6000L // TODO: get actual value

    val timeData = remember { mutableStateOf(millisInFuture) }
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Status(gameViewModel)
        MostrarPokemon(viewModel = gameViewModel)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            //Tempo(gameViewModel)
            val countDownTimer =
                object : CountDownTimer(millisInFuture, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        Log.d("TAG", "onTick: ")
                        timeData.value = millisUntilFinished
                    }

                    override fun onFinish() {
                        gameViewModel.Evaluar(true, 5)
                    }
                }

            DisposableEffect(key1 = "key") {
                countDownTimer.start()
                onDispose {
                    countDownTimer.cancel()
                }
            }

            Text(
                text = stringResource(R.string.tiempo, (timeData.value / 1000)),
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
            )
        }
        MostrarRespuestas(viewModel = gameViewModel, tiempo = 5 - (timeData.value.toInt() / 1000))
    }
}

@Composable
fun ResultadoScreen(
    gameViewModel: GameViewModel
) {
    val data = gameViewModel.uiStateData.collectAsState().value

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Status(gameViewModel)
        MostrarPokemon(viewModel = gameViewModel)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Resultado(viewModel = gameViewModel)
        }
        MostrarRespuestas2(viewModel = gameViewModel)
    }
}

@Composable
fun Status(
    //vidas: Int,
    //puntos: Int,
    gameViewModel: GameViewModel,
    modifier: Modifier = Modifier
) {
    val data = gameViewModel.uiStateData.collectAsState().value
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .size(48.dp),
    ) {
        Text(
            text = stringResource(R.string.vidas, data.vidas),
            fontSize = 18.sp,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
            text = stringResource(R.string.puntos, data.puntos),
            fontSize = 18.sp,
        )
    }
}

@Composable
fun MostrarPokemon(
    viewModel: GameViewModel
) {
    val data = viewModel.uiStateData.collectAsState().value
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        Text(
            text = stringResource(R.string.pregunta),
            fontSize = 45.sp,
            //modifier = modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )

        var id = data.pokemonActual.id.toString()
        var n = id.length
        if (n == 1) {
            id = "00" + id
        } else if (n == 2) {
            id = "0" + id
        }
        if (data.esCorrecto) {
            MostrarImagen(ruta = "images/$id.png")
        } else {
            MostrarImagenSilueta(ruta = "images/$id.png")
        }
    }
}

@Composable
fun MostrarRespuestas(
    viewModel: GameViewModel,
    tiempo: Int
) {
    val data = viewModel.uiStateData.collectAsState().value

    //amos a calcular cuantas respuestas posibles hay para el caso de que ya llegue a los 148 pokemones
    val son3: Boolean = data.cuatroPokemonsDesordenado.size == 3
    val son2: Boolean = data.cuatroPokemonsDesordenado.size == 2
    val es1: Boolean = data.cuatroPokemonsDesordenado.size == 1

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            onClick = {
                viewModel.opcionElegida(0)
                viewModel.Evaluar(seAgotoElTiempo = false, tiempo = tiempo)
            },
            modifier = Modifier
                .width(150.dp)
                .height(50.dp)
                .padding(end = 8.dp)
        ) {
            Text(data.cuatroPokemonsDesordenado[0].name)
        }

        Button(
            onClick = {
                viewModel.opcionElegida(1)
                viewModel.Evaluar(false, tiempo = tiempo)
            },
            modifier = Modifier
                .width(150.dp)
                .height(50.dp)
                .padding(end = 8.dp),
            enabled = !es1
        ) {
            Text(if (es1) "" else data.cuatroPokemonsDesordenado[1].name)
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            onClick = {
                viewModel.opcionElegida(2)
                viewModel.Evaluar(false, tiempo = tiempo)
            },
            modifier = Modifier
                .width(150.dp)
                .height(50.dp)
                .padding(end = 8.dp),
            enabled = !son2 && !es1
        ) {
            Text(if (son2 || es1) "" else data.cuatroPokemonsDesordenado[2].name)
        }

        Button(
            onClick = {
                viewModel.opcionElegida(3)
                viewModel.Evaluar(false, tiempo = tiempo)
            },
            modifier = Modifier
                .width(150.dp)
                .height(50.dp)
                .padding(end = 8.dp),
            enabled = !son3 && !son2 && !es1
        ) {
            Text(if (son3 || son2 || es1) "" else data.cuatroPokemonsDesordenado[3].name)
        }
    }
}

@Composable
fun Resultado(
    viewModel: GameViewModel
) {
    val data = viewModel.uiStateData.collectAsState().value
    Column(
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {

        Text(
            text = if (data.esCorrecto) "¡Correcto!" else "Incorrecto...",
            fontSize = 25.sp,
            //modifier = modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            color = if (data.esCorrecto) Color.Green else Color.Red
        )
    }
}

@Composable
fun MostrarRespuestas2(
    viewModel: GameViewModel
) {
    val data = viewModel.uiStateData.collectAsState().value

    //amos a calcular cuantas respuestas posibles hay para el caso de que ya llegue a los 148 pokemones
    val son3: Boolean = data.cuatroPokemonsDesordenado.size == 3
    val son2: Boolean = data.cuatroPokemonsDesordenado.size == 2
    val es1: Boolean = data.cuatroPokemonsDesordenado.size == 1

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            onClick = {
                viewModel.opcionElegida(0)
            },
            modifier = Modifier
                .width(150.dp)
                .height(50.dp)
                .padding(end = 8.dp),
            enabled = false,
            colors = ButtonDefaults.buttonColors(
                disabledBackgroundColor = if (data.listaRespuestaElegida.last() == 0 && !data.seAgotoElTiempo.last()) {
                    if (data.esCorrecto) Color.Green else Color.Red
                } else Color.LightGray
            )
        ) {
            Text(data.cuatroPokemonsDesordenado[0].name)
        }

        Button(
            onClick = {
                viewModel.opcionElegida(1)
            },
            modifier = Modifier
                .width(150.dp)
                .height(50.dp)
                .padding(end = 8.dp),
            enabled = false,
            colors = ButtonDefaults.buttonColors(
                disabledBackgroundColor = if (data.listaRespuestaElegida.last() == 1 && !data.seAgotoElTiempo.last()) {
                    if (data.esCorrecto) Color.Green else Color.Red
                } else Color.LightGray
            )
        ) {
            Text(if (es1) "" else data.cuatroPokemonsDesordenado[1].name)
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            onClick = {
                viewModel.opcionElegida(2)
            },
            modifier = Modifier
                .width(150.dp)
                .height(50.dp)
                .padding(end = 8.dp),
            enabled = false,
            colors = ButtonDefaults.buttonColors(
                disabledBackgroundColor = if (data.listaRespuestaElegida.last() == 2 && !data.seAgotoElTiempo.last()) {
                    if (data.esCorrecto) Color.Green else Color.Red
                } else Color.LightGray
            )
        ) {
            Text(if (son2 || es1) "" else data.cuatroPokemonsDesordenado[2].name)
        }

        Button(
            onClick = {
                viewModel.opcionElegida(3)
            },
            modifier = Modifier
                .width(150.dp)
                .height(50.dp)
                .padding(end = 8.dp),
            enabled = false,
            colors = ButtonDefaults.buttonColors(
                disabledBackgroundColor = if (data.listaRespuestaElegida.last() == 3 && !data.seAgotoElTiempo.last()) {
                    if (data.esCorrecto) Color.Green else Color.Red
                } else Color.LightGray
            )
        ) {
            Text(if (son3 || son2 || es1) "" else data.cuatroPokemonsDesordenado[3].name)
        }
    }
}

@Composable
fun Tempo(viewModel: GameViewModel) {
    val millisInFuture = 6000L // TODO: get actual value

    val timeData = remember { mutableStateOf(millisInFuture) }

    val countDownTimer =
        object : CountDownTimer(millisInFuture, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.d("TAG", "onTick: ")
                timeData.value = millisUntilFinished
            }

            override fun onFinish() {
                viewModel.Evaluar(true, 5)
            }
        }

    DisposableEffect(key1 = "key") {
        countDownTimer.start()
        onDispose {
            countDownTimer.cancel()
        }
    }

    Text(
        text = stringResource(R.string.tiempo, (timeData.value / 1000)),
        fontSize = 25.sp,
        textAlign = TextAlign.Center,
    )
}

@Composable
fun getAssetBitmap(context: Context, assetPath: String): Bitmap {
    return BitmapFactory.decodeStream(context.assets.open(assetPath))
}

@Composable
fun MostrarImagen(ruta: String, modifier: Modifier = Modifier) {
    val bitmap = getAssetBitmap(LocalContext.current, ruta)
    val painter = BitmapPainter(bitmap.asImageBitmap())

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painter,
            contentDescription = "pokemon",
            //colorFilter = ColorFilter.tint(Color.Black),
            modifier = Modifier.size(200.dp),
            alignment = Alignment.Center
        )
    }
}

@Composable
fun MostrarImagenSilueta(ruta: String, modifier: Modifier = Modifier) {
    val bitmap = getAssetBitmap(LocalContext.current, ruta)
    val painter = BitmapPainter(bitmap.asImageBitmap())

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painter,
            contentDescription = "pokemon",
            colorFilter = ColorFilter.tint(Color.Black),
            modifier = Modifier.size(200.dp),
            alignment = Alignment.Center
        )
    }
}

@Composable
fun MostrarImagen2(ruta: String, modifier: Modifier = Modifier) {
    val bitmap = getAssetBitmap(LocalContext.current, ruta)
    val painter = BitmapPainter(bitmap.asImageBitmap())
    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier
            .size(130.dp)
            .padding(8.dp),
        contentScale = ContentScale.Fit,
    )
}

@Composable
fun MostrarImagenSilueta2(ruta: String, modifier: Modifier = Modifier) {
    val bitmap = getAssetBitmap(LocalContext.current, ruta)
    val painter = BitmapPainter(bitmap.asImageBitmap())
    Image(
        painter = painter,
        contentDescription = "pokemon",
        colorFilter = ColorFilter.tint(Color.Black),
        modifier = Modifier
            .size(130.dp)
            .padding(8.dp),
        contentScale = ContentScale.Fit,
    )
}

@Composable
private fun FinalScoreDialog(
    viewModel: GameViewModel,
    player: Player,
    modifier: Modifier = Modifier
) {
    //val activity = (LocalContext.current as Activity)
    var datos = ""

    datos = "Name: ${player.name}\n Score: ${player.score}\n Country: ${player.country}\n " +
            "Time: ${player.time}\n Attemps: ${player.attemps}"

    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
        },
        title = { stringResource(R.string.mensaje_datos)},
        text = { datos },
        modifier = modifier,
        /*dismissButton = {
            TextButton(
                onClick = {
                    activity.finish()
                }
            ) {
                Text(text = "Salir")
            }
        }*/
        confirmButton = {
            TextButton(
                onClick = { viewModel.MostrarTop() }
            ) {
                Text(text = "Okay")
            }
        }
    )
}

@Composable
fun ScreenJuegoTerminado(
    gameViewModel: GameViewModel,
    puntos: Int,
    listaPregunta: MutableList<Quadruple<Pokemon, Pokemon, Pokemon, Pokemon>>,
    listaPokemonCorrecto: MutableList<Pokemon>,
    listaRespuestaElegida: MutableList<Int>,
    seAgotoElTiempo: MutableList<Boolean>,
    modifier: Modifier = Modifier
) {
    val data = gameViewModel.uiStateData.collectAsState().value
    val activity = (LocalContext.current as Activity)

    var progreso = listaPokedex.size
    var porcentaje: Float = listaPokedex.size.toFloat() / listaPokemon.size.toFloat()

    Column(
        modifier = Modifier
            //.verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = stringResource(R.string.progreso, progreso),
            modifier = Modifier.padding(top = 20.dp, bottom = 0.dp, start = 0.dp, end = 15.dp),
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
            //.padding(),
            //horizontalArrangement = Arrangement.Center
        ) {
            LinearProgressIndicator(
                progress = porcentaje,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Text(
            text = stringResource(R.string.tu_puntuacion, puntos),
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 10.dp)
        )
        Row(
            modifier = modifier
                .fillMaxWidth(),
            //.padding(15.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedButton(
                onClick = {
                    //activity.finish()
                    gameViewModel.Inicio()
                },
                modifier = Modifier
                    .width(170.dp)
                    .height(40.dp)
                    .padding(end = 8.dp, top = 5.dp)
            ) {
                Text("Regresar a inicio")
            }
            Button(
                onClick = {
                    gameViewModel.CargandoPokemones()
                },
                modifier = Modifier
                    .width(170.dp)
                    .height(40.dp)
                    .padding(end = 8.dp, top = 5.dp)
            ) {
                Text("Jugar de nuevo")
            }
        }

        var listaPreguntas: MutableList<Preguntas> = mutableListOf()
        var n = listaPregunta.size
        var i = 0
        do {
            var pregunta: Preguntas = Preguntas()
            var opCorrecta: Int = 0
            var p = listaPregunta[i]

            if (p.first == listaPokemonCorrecto[i]) {
                opCorrecta = 0
            } else if (p.second == listaPokemonCorrecto[i]) {
                opCorrecta = 1
            } else if (p.third == listaPokemonCorrecto[i]) {
                opCorrecta = 2
            } else if (p.fourth == listaPokemonCorrecto[i]) {
                opCorrecta = 3
            }

            pregunta.id = listaPokemonCorrecto[i].id
            pregunta.opcionCorrecta = opCorrecta
            pregunta.opcionElegida = listaRespuestaElegida[i]
            pregunta.pokemones = mutableListOf(
                listaPregunta[i].first,
                listaPregunta[i].second,
                listaPregunta[i].third,
                listaPregunta[i].fourth
            )
            pregunta.esCorrecto =
                pregunta.pokemones[pregunta.opcionElegida] == pregunta.pokemones[pregunta.opcionCorrecta]
            pregunta.seAgotoElTiempo = seAgotoElTiempo[i]
            listaPreguntas.add(pregunta)
            i++
        } while (i != n)

        ListarPreguntas(lista = listaPreguntas)
    }
}

@Composable
fun ListarPreguntas(
    lista: List<Preguntas>
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 25.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Tus respuestas",
                    fontSize = 30.sp
                )
            }
        }
        items(lista) { pregunta ->
            QuestionsCard(
                id = pregunta.id,
                esCorrecto = pregunta.esCorrecto,
                pokemones = pregunta.pokemones,
                opcionElegida = pregunta.opcionElegida,
                opcionCorrecta = pregunta.opcionCorrecta,
                seAgotoElTiempo = pregunta.seAgotoElTiempo
            )
        }
    }
}


@Composable
fun QuestionsCard(
    id: Int,
    esCorrecto: Boolean,
    pokemones: List<Pokemon>,
    opcionElegida: Int,
    opcionCorrecta: Int,
    seAgotoElTiempo: Boolean
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            var ruta = id.toString()
            var n = ruta.length
            if (n == 1) {
                ruta = "00" + id
            } else if (n == 2) {
                ruta = "0" + id
            }

            Column(
                Modifier.padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (esCorrecto) {
                    MostrarImagen2(ruta = "images/$ruta.png")
                } else {
                    MostrarImagenSilueta2(ruta = "images/$ruta.png")
                }

                Text(
                    text = if (esCorrecto) "¡Correcto!" else "Incorrecto...",
                    style = MaterialTheme.typography.h4,
                    color = if (esCorrecto) Color.Green else Color.Red,
                )
                MostrarRespuestas3(
                    listaPoke = pokemones,
                    opcionElegida = opcionElegida,
                    opcionCorrecta = opcionCorrecta,
                    esCorrecto = esCorrecto,
                    seAgotoElTiempo = seAgotoElTiempo
                )
            }
        }
    }
}

@Composable
fun MostrarRespuestas3(
    listaPoke: List<Pokemon>,
    opcionElegida: Int,
    opcionCorrecta: Int,
    esCorrecto: Boolean,
    seAgotoElTiempo: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            onClick = { },
            modifier = Modifier
                .width(150.dp)
                .height(50.dp)
                .padding(end = 8.dp),
            enabled = false,
            colors = ButtonDefaults.buttonColors(
                disabledBackgroundColor = if (esCorrecto) {
                    if (opcionElegida == 0) Color.Green else Color.Gray
                } else {
                    if (opcionElegida == 0 && !seAgotoElTiempo) Color.Red else Color.Gray
                }
            )
        ) {
            Text(listaPoke[0].name)
        }

        Button(
            onClick = { },
            modifier = Modifier
                .width(150.dp)
                .height(50.dp)
                .padding(end = 8.dp),
            enabled = false,
            colors = ButtonDefaults.buttonColors(
                disabledBackgroundColor = if (esCorrecto) {
                    if (opcionElegida == 1) Color.Green else Color.Gray
                } else {
                    if (opcionElegida == 1 && !seAgotoElTiempo) Color.Red else Color.Gray
                }
            )
        ) {
            Text(listaPoke[1].name)
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            onClick = { },
            modifier = Modifier
                .width(150.dp)
                .height(50.dp)
                .padding(end = 8.dp),
            enabled = false,
            colors = ButtonDefaults.buttonColors(
                disabledBackgroundColor = if (esCorrecto) {
                    if (opcionElegida == 2) Color.Green else Color.Gray
                } else {
                    if (opcionElegida == 2 && !seAgotoElTiempo) Color.Red else Color.Gray
                }
            )
        ) {
            Text(listaPoke[2].name)
        }

        Button(
            onClick = { },
            modifier = Modifier
                .width(150.dp)
                .height(50.dp)
                .padding(end = 8.dp),
            enabled = false,
            colors = ButtonDefaults.buttonColors(
                disabledBackgroundColor = if (esCorrecto) {
                    if (opcionElegida == 3) Color.Green else Color.Gray
                } else {
                    if (opcionElegida == 3 && !seAgotoElTiempo) Color.Red else Color.Gray
                }
            )
        ) {
            Text(listaPoke[3].name)
        }
    }
}

@Composable
fun PedirDatosDelEntrenador(
    viewModel: GameViewModel
) {
    ImageFondo("fondoAtardecer.jpg", true)
    var name by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Ingresa tus datos para registrarte en el Top",
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre del entrenador pokemon") },
            modifier = Modifier.fillMaxWidth()
        )
        Dropdown(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            selected = country,
            label = "País",
            listItems = countryList,
            onValueChange = { country = it }
        )
        Button(
            onClick = {
                viewModel.GuardarDatosEntrenador(name, country)
                currentPlayer?.score = currentPlayer?.let { CalculateScore(it) }!!
            },
            modifier = Modifier.padding(10.dp)
        ) {
            Text("Guardar")
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Dropdown(
    modifier: Modifier,
    selected: String,
    label: String,
    listItems: List<String>,
    onValueChange: (String) -> Unit
) {
    var open by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = open,
        onExpandedChange = { open = it }
    ) {
        TextField(
            value = selected,
            label = { Text(text = label) },
            onValueChange = { onValueChange(selected) },
            modifier = modifier,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = open)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            readOnly = true
        )
        if (listItems.isNotEmpty()) {
            ExposedDropdownMenu(
                expanded = open,
                onDismissRequest = { open = false }
            ) {
                listItems.forEach { selectedOption ->
                    DropdownMenuItem(
                        onClick = {
                            onValueChange(selectedOption)
                            open = false
                        }
                    ) {
                        Text(text = selectedOption)
                    }
                }
            }
        }
    }
}

@Composable
fun ScreenReady(
    top1: List<Player>,
    viewModel: GameViewModel,
) {
    ImageFondo("fondoDia.jpg", true)

    var top: MutableList<Player> = mutableListOf()
    top1.forEach {
        var n = 0
        it.pokedex.forEach {
            if (it.discover == true) {
                n++
            }
        }
        if (n == 151) {
            top.add(it)
        }
    }
    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 25.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Top Maestros Pokemon",
                fontSize = 30.sp,
                color = Color.White
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 25.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = { viewModel.Inicio() },
                colors = ButtonDefaults.buttonColors(Purple500)
            ) {
                Text(text = "Regresar", color = Color.White)
            }
        }

        var n = 0
        top.sortedByDescending { it.score }.forEach {
            Row(modifier = Modifier.padding(bottom = 10.dp)) {
                Caja(viewModel, n, it)
            }
            n++
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

//colores que uso para mis cajas :>
var colores: List<Color> = listOf(golden, silverDark, bronze)
var coloresIcono: List<Color> = listOf(goldenDark, silver, bronzeDark)

@Composable
fun Caja(
    /*name: String,
    country: String,
    score: Int,*/
    viewModel: GameViewModel,
    n: Int,
    player: Player
) {
    Box(
        Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(if (n < 3) colores[n] else gray)
            .padding(20.dp)
            //.clickable { viewModel.MostrarDatosPlayer(player) }
    ) {
        Column() {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (n < 3) {
                    Icon(
                        Icons.Default.WorkspacePremium,
                        modifier = Modifier
                            .padding(end = 10.dp, top = 0.dp)
                            .size(70.dp),
                        contentDescription = "",
                        tint = coloresIcono[n]
                    )
                }
                Text(
                    text = (n + 1).toString(),
                    fontSize = if (n < 3) 30.sp else 17.sp,
                    fontWeight = if (n < 3) FontWeight.Bold else FontWeight.Normal,
                    color = if (n < 3) coloresIcono[n] else Color.Gray
                )
                Column(modifier = Modifier.padding(start = 20.dp)) {
                    Row(modifier = Modifier.padding(bottom = 5.dp)) {
                        Text(
                            player.name,
                            color = if (n < 3) coloresIcono[n] else Color.Gray,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    )
                    {
                        Text(
                            text = "Country: ${player.country}",
                            color = if (n < 3) coloresIcono[n] else Color.Gray,
                            fontWeight = FontWeight.Medium
                        )
                        ImageCountry(ruta = "banderas/${player.country}.png")

                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Score: ${player.score}",
                            color = if (n < 3) coloresIcono[n] else Color.Gray,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ImageCountry(ruta: String, modifier: Modifier = Modifier) {
    val bitmap = getAssetBitmap(LocalContext.current, ruta)
    val painter = BitmapPainter(bitmap.asImageBitmap())

    Image(
        painter = painter,
        contentDescription = "country",
        modifier = Modifier.size(40.dp),
        //alignment = Alignment.Center
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    PokeTecTheme() {

    }
}
package com.almy.poketec.screens.game

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.CountDownTimer
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.almy.poketec.R
import com.almy.poketec.data.listaPokemon
import com.almy.poketec.screens.pokedex.Pokemon
import com.almy.poketec.ui.theme.PokeTecTheme
import com.game.guesspoke.screens.game.*
import kotlinx.coroutines.delay
import java.util.*
import kotlin.concurrent.timerTask

@Composable
fun GameScreen1(viewModel: GameViewModel) {
    val state = viewModel.uiState.collectAsState().value
    when (state) {
        is ScreenUiState.CargarPokemon -> {}
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
        is ScreenUiState.PokedexCompletada -> {}
        else -> {

        }
    }
}

@Composable
fun MostrarPokemonScreen(
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
            Tempo(gameViewModel)
        }
        MostrarRespuestas(viewModel = gameViewModel)
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
    viewModel: GameViewModel
) {
    val data = viewModel.uiStateData.collectAsState().value
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Button(
            onClick = {
                viewModel.opcionElegida(0)
                viewModel.Evaluar(seAgotoElTiempo = false)
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
                viewModel.Evaluar(false)
            },
            modifier = Modifier
                .width(150.dp)
                .height(50.dp)
                .padding(end = 8.dp)
        ) {
            Text(data.cuatroPokemonsDesordenado[1].name)
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
                viewModel.Evaluar(false)
            },
            modifier = Modifier
                .width(150.dp)
                .height(50.dp)
                .padding(end = 8.dp)
        ) {
            Text(data.cuatroPokemonsDesordenado[2].name)
        }

        Button(
            onClick = {
                viewModel.opcionElegida(3)
                viewModel.Evaluar(false)
            },
            modifier = Modifier
                .width(150.dp)
                .height(50.dp)
                .padding(end = 8.dp)
        ) {
            Text(data.cuatroPokemonsDesordenado[3].name)
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
            Text(data.cuatroPokemonsDesordenado[1].name)
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
            Text(data.cuatroPokemonsDesordenado[2].name)
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
            Text(data.cuatroPokemonsDesordenado[3].name)
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
                viewModel.Evaluar(true)
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
    gameViewModel: GameViewModel,
    puntos: Int,
    modifier: Modifier = Modifier
) {
    val data = gameViewModel.uiStateData.collectAsState().value
    val activity = (LocalContext.current as Activity)

    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
        },
        title = { Text(stringResource(R.string.mensaje)) },
        text = { Text(stringResource(R.string.tu_puntuacion, puntos)) },
        modifier = modifier,
        dismissButton = {
            TextButton(
                onClick = {
                    activity.finish()
                }
            ) {
                Text(text = "Salir")
            }
        },
        confirmButton = {
            TextButton(
                onClick = { gameViewModel.loadPokemon() }
            ) {
                Text(text = "Jugar de nuevo")
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
                    activity.finish()
                },
                modifier = Modifier
                    .width(170.dp)
                    .height(40.dp)
                    .padding(end = 8.dp, top = 5.dp)
            ) {
                Text("Salir")
            }
            Button(
                onClick = {
                    gameViewModel.loadPokemon()
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    PokeTecTheme(){

    }
}
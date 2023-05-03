package com.game.simondicevm.ui

import android.app.Activity
import android.app.Application
import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.game.simondicevm.R
import com.game.simondicevm.ui.topstate.TopScreen
import com.game.simondicevm.ui.topstate.TopViewModel
import com.rigo.simondice.domain.models.Player

@Composable
fun GameScreen(viewModel: SimonViewModel, viewModel2: TopViewModel)
{
    val state = viewModel.uiState.collectAsState().value
    when (state) {
        is ScreenUiState.Inicio -> InicioScreen(viewModel, viewModel2)
        is ScreenUiState.TurnoSimon -> SimonScreen(viewModel, state.nivel)
        is ScreenUiState.TurnoJugador -> JugadorScreen(viewModel, state.mensaje, state.nivel)
        is ScreenUiState.TopResultados -> {
            //TopScreen(viewModel = viewModel, listaTop = state.listaTop)
            TopScreen(viewModel = viewModel2, simonViewModel = viewModel)
            }
        is ScreenUiState.RegistrarJugador -> RegistrarScreen(puntos = state.puntos, viewModel = viewModel)
        else -> {

        }
    }
}


@Composable
fun InicioScreen(
    viewModel: SimonViewModel,
    topViewModel: TopViewModel
)
{
    val data = viewModel.uiStateData.collectAsState().value
    val activity = (LocalContext.current as? Activity)
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Simon dice", fontSize = 35.sp)
        Text(text = "Record: Nivel ${data.record}", fontSize = 20.sp, modifier = Modifier.padding(10.dp))
        Row(horizontalArrangement = Arrangement.Center) {
            Button(
                onClick = { viewModel.MostrarTop()},
                modifier = Modifier
                    .padding(20.dp)
                    .width(150.dp)
                    .height(50.dp)
            ) {
                Text(text = "Leaderboard")
            }
        }

        Row(horizontalArrangement = Arrangement.Center) {
            Button(
                modifier = Modifier
                    .padding(20.dp)
                    .width(150.dp)
                    .height(50.dp),
                onClick = { /* topViewModel.postTopFake(Player(null,"test",5,1)) */ }
            ) {
                Text(text = "Post request")
            }
        }

        Row(horizontalArrangement = Arrangement.SpaceEvenly) {
            OutlinedButton(
                onClick = { activity?.finish() },
                modifier = Modifier
                    .padding(20.dp)
                    .width(150.dp)
                    .height(50.dp)
            ) {
                Text(text = "Salir")
            }
            Button(
                onClick = { viewModel.PrepararBotones() },
                modifier = Modifier
                    .padding(20.dp)
                    .width(150.dp)
                    .height(50.dp)
            ) {
                Text(text = "Jugar")
            }
        }
    }
}

@Composable
fun SimonScreen(
    viewModel: SimonViewModel,
    nvl: Int
)
{
    var context: Context = LocalContext.current
    LaunchedEffect(key1 = true)
    {
        viewModel.reproducirCombinacion(viewModel.GenerarCombinacion(nvl), context)
    }

    val data = viewModel.uiStateData.collectAsState().value
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        estadisticas(msj = "Simon dice...", nvl = data.nivel)
        Row (
            modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            unBoton(
                onClick = {  },
                enabled = data.listaBoton[0].encendido,
                text = data.listaBoton[0].nombre,
                colorOn = data.listaBoton[0].colorOn,
                colorOff = data.listaBoton[0].colorOff
            )
            unBoton(
                onClick = { },
                enabled = data.listaBoton[1].encendido,
                text = data.listaBoton[1].nombre,
                colorOn = data.listaBoton[1].colorOn,
                colorOff = data.listaBoton[1].colorOff
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            unBoton(
                onClick = { },
                enabled = data.listaBoton[2].encendido,
                text = data.listaBoton[2].nombre,
                colorOn = data.listaBoton[2].colorOn,
                colorOff = data.listaBoton[2].colorOff
            )
            unBoton(
                onClick = { },
                enabled = data.listaBoton[3].encendido,
                text = data.listaBoton[3].nombre,
                colorOn = data.listaBoton[3].colorOn,
                colorOff = data.listaBoton[3].colorOff
            )
        }
    }
}



@Composable
fun JugadorScreen(
    viewModel: SimonViewModel,
    msj: String,
    nvl: Int
)
{
    var greenOn by remember { mutableStateOf(false) }
    var redOn by remember { mutableStateOf(false) }
    var blueOn by remember { mutableStateOf(false) }
    var yellowOn by remember { mutableStateOf(false) }
    var context: Context = LocalContext.current

    LaunchedEffect(key1 = greenOn)
    {
        if(greenOn)
        {
            viewModel.Evaluar("0", context)
            greenOn = !greenOn
        }
    }
    LaunchedEffect(key1 = redOn)
    {
        if(redOn)
        {
            viewModel.Evaluar("1", context)
            redOn = !redOn
        }
    }
    LaunchedEffect(key1 = yellowOn)
    {
        if(yellowOn)
        {
            viewModel.Evaluar("3", context)
            yellowOn = !yellowOn
        }
    }
    LaunchedEffect(key1 = blueOn)
    {
        if(blueOn)
        {
            viewModel.Evaluar("2", context)
            blueOn = !blueOn
        }
    }

    val data = viewModel.uiStateData.collectAsState().value
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        estadisticas(msj = msj, nvl = nvl)
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            unBoton(
                onClick = { greenOn = !greenOn },
                enabled = data.listaBoton[0].encendido,
                text = data.listaBoton[0].nombre,
                colorOn = data.listaBoton[0].colorOn,
                colorOff = data.listaBoton[0].colorOff
            )
            unBoton(
                onClick = { redOn = !redOn },
                enabled = data.listaBoton[1].encendido,
                text = data.listaBoton[1].nombre,
                colorOn = data.listaBoton[1].colorOn,
                colorOff = data.listaBoton[1].colorOff
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            unBoton(
                onClick = { blueOn = !blueOn },
                enabled = data.listaBoton[2].encendido,
                text = data.listaBoton[2].nombre,
                colorOn = data.listaBoton[2].colorOn,
                colorOff = data.listaBoton[2].colorOff
            )
            unBoton(
                onClick = { yellowOn = !yellowOn },
                enabled = data.listaBoton[3].encendido,
                text = data.listaBoton[3].nombre,
                colorOn = data.listaBoton[3].colorOn,
                colorOff = data.listaBoton[3].colorOff
            )
        }
    }
}

@Composable
fun estadisticas(
    msj: String,
    nvl: Int
){
    Row(
        modifier= Modifier
            .fillMaxWidth()
            .padding(19.dp, 3.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("$msj", fontFamily = FontFamily.SansSerif, fontSize = 20.sp)
        Text("  Nivel: $nvl", fontFamily = FontFamily.SansSerif, fontSize = 20.sp)
    }
}

@Composable
fun unBoton(
    onClick: () -> Unit,
    enabled: Boolean,
    text: String,
    colorOn: Color,
    colorOff: Color
){
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .padding(2.dp)
            .width(180.dp)
            .height(170.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorOff,
            disabledBackgroundColor = colorOn
        )
    ) {
        Text(text, fontSize = 15.sp)
    }
}

@Composable
fun TopScreen(
    viewModel: SimonViewModel,
    listaTop: List<top>
){
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        var n = 0
        if(listaTop.size == 0)
        {
            Row() {
                Text(text = "No hay resultados",
                    modifier = Modifier.testTag("estaVacio"))
            }
        }
        else
        {
            for (i in listaTop.sortedByDescending { it.puntos })
            {
                if(n<10)
                {
                    Row() {
                        Text(text = "${i.nombre}, ${i.puntos} puntos.",
                            modifier = Modifier.testTag("top$n")
                        )
                    }
                    n++
                }
            }
        }

        Button(onClick = { viewModel.RegresarInicio() }) {
            Text(text = "Volver a inicio")
        }
    }
}

@Composable
fun RegistrarScreen(
    puntos: Int,
    viewModel: SimonViewModel
){
    val data = viewModel.uiStateData.collectAsState().value
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(19.dp, 15.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                "¡Estás dentro del Top 10 de puntuaciones!",
                fontSize = 50.sp
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(19.dp, 15.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                "Ingresa tu nombre",
                fontSize = 20.sp
            )
        }
        CustomInput(
            label = "Nombre",
            value = data.nombre,
            onChangeValue = { viewModel.actualizarNombre(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            focusManager = focusManager
        )
        Button(
            onClick = { viewModel.RegistrarJugador(puntos) },
            modifier = Modifier
                .padding(10.dp)
                .height(60.dp)
        ) {
            Text(text = "Registrar puntuacion")
        }
    }
}

@Composable
fun CustomInput(
    label: String,
    value: String,
    onChangeValue: (String) -> Unit,
    modifier: Modifier,
    focusManager: FocusManager
){
    OutlinedTextField(
        value = value,
        onValueChange = onChangeValue,
        modifier = modifier,
        label = { Text(label) },
        isError = false,
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            KeyboardCapitalization.Words,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions(
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        )
    )
}
package com.gato


import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gato.ui.theme.GatoTheme
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var accionesJugador1 = mutableListOf<Int>()
            var accionesJugador2 = mutableListOf<Int>()

            var gana0 = mutableListOf<Int>(1, 2, 3)
            var gana1 = mutableListOf<Int>(7, 5, 3)
            var gana2 = mutableListOf<Int>(1, 5, 9)
            var gana3 = mutableListOf<Int>(1, 4, 7)
            var gana4 = mutableListOf<Int>(4, 5, 6)
            var gana5 = mutableListOf<Int>(7, 8, 9)
            var gana6 = mutableListOf<Int>(7, 8, 9)
            var gana7 = mutableListOf<Int>(2, 5, 8)
            var gana8 = mutableListOf<Int>(3, 6, 9)
            var gana9 = mutableListOf<Int>(7, 8, 9)

            var regla = listOf(gana0, gana1, gana2, gana3, gana4, gana5, gana6, gana7, gana8)

            var match = mutableListOf<Any>()
            var casilla by remember { mutableStateOf(0) }
            var jugador by remember { mutableStateOf(1) }
            var jugadas by remember { mutableStateOf(0) }
            var ganador by remember { mutableStateOf(0) }

            var mensaje = false
            var verficiar = false

            GatoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    if ((jugadas % 2) != 0) {
                        //accionesJugador1.clear()
                        accionesJugador1.add(casilla)
                        casilla = 0
                    } else {
                        //accionesJugador2.clear()
                        accionesJugador2.add(casilla)
                        casilla = 0
                    }

                    if ((jugadas % 2) != 0)
                        jugador = 2
                    else
                        jugador = 1
                    juego(accionesJugador1, accionesJugador2, casilla, jugador, jugadas,
                        agregarJugada = { jugadas++ },
                        seleccionCasilla = { casilla++ }, limpiar = { casilla = 0 }, match
                    )

                    if (jugadas > 4 && jugadas < 9) {
                        for (i in 0..8) {
                            val matches = accionesJugador1.intersect(regla[i])
                            //println(matches)
                            if (matches.count() > 2) {
                                match = matches.toMutableList()
                                ganador = 1
                            }
                        }

                        if (match.isEmpty()) {
                            for (i in 0..8) {
                                val matches = accionesJugador2.intersect(regla[i])
                                //println(matches)
                                if (matches.count() > 2) {
                                    match = matches.toMutableList()
                                    ganador = 2
                                }
                            }

                        }

                        if (ganador != 0) {
                            mensaje = true
                        }


                    } else {
                        if (jugadas == 9) {

                            mensaje = true
                            if (mensaje) {
                                verficiar = alertDialogSample(
                                    ganador, reinicio = { ganador = 0 })
                                accionesJugador1 = mutableListOf<Int>()
                                accionesJugador2 = mutableListOf<Int>()
                                casilla = 0
                                jugador = 1
                                match = mutableListOf<Any>()
                                LaunchedEffect(ganador) {
                                    delay(5000)
                                    jugadas = 0
                                    verficiar = true
                                }
                            }

                            if (verficiar) {
                                mensaje = false
                            }
                        }
                        //ganador = 0

                    }
                    if (ganador != 0) {

                        if (mensaje) {
                            verficiar = alertDialogSample(
                                ganador, reinicio = { ganador = 0 }, limpiar = {jugadas = 0})
                            accionesJugador1 = mutableListOf<Int>()
                            accionesJugador2 = mutableListOf<Int>()
                            casilla = 0
                            //jugadas = 0
                            jugador = 1
                            match = mutableListOf<Any>()
                        }
                        if (verficiar) {
                            mensaje = false
                        }
                    }
                    //ganador = 0

                }

            }
        }
    }
}

@Composable
fun alertDialogSample(
    jugador: Int, reinicio: () -> Unit, limpiar: () -> Unit
): Boolean {
    var x by remember { mutableStateOf(false) }
    val activity = (LocalContext.current as? Activity)
    MaterialTheme {
        Column {
            val openDialog = remember { mutableStateOf(true) }

            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    title = {
                        Text(
                            text = "Ganador del juego",
                            fontSize = 35.sp,
                            fontWeight = FontWeight(600)
                        )
                        //Text(text = name, fontSize = 35.sp, fontWeight = FontWeight(600))
                    },
                    text = {
                        if (jugador == 0) Text(
                            "Es un empate",
                            fontSize = 25.sp
                        ) else Text("El ganador es: $jugador", fontSize = 25.sp)
                    },
                    confirmButton = {
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                            onClick = {
                                openDialog.value = false
                                x = true
                                reinicio()
                                limpiar()
                            }) {
                            Text("Reiniciar", fontSize = 20.sp, color = Color.White)
                        }
                    },
                    dismissButton = {
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                            onClick = {
                                activity?.finish()
                            }) {
                            Text("Salir", fontSize = 20.sp, color = Color.White)
                        }
                    }
                )
            }
            // x= true
        }

    }
    return x
}

@Composable
fun alertDialogSample(
    jugador: Int, reinicio: () -> Unit
): Boolean {
    var x by remember { mutableStateOf(false) }
    val activity = (LocalContext.current as? Activity)
    MaterialTheme {
        Column {
            val openDialog = remember { mutableStateOf(true) }

            if (openDialog.value) {
                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    title = {
                        Text(
                            text = "Ganador del juego",
                            fontSize = 35.sp,
                            fontWeight = FontWeight(600)
                        )
                        //Text(text = name, fontSize = 35.sp, fontWeight = FontWeight(600))
                    },
                    text = {
                        if (jugador == 0) Text(
                            "Es un empate",
                            fontSize = 25.sp
                        ) else Text("El ganador es: $jugador", fontSize = 25.sp)
                    },
                    confirmButton = {
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                            onClick = {
                                openDialog.value = false
                                x = true
                            }) {
                            Text("Reiniciar", fontSize = 20.sp, color = Color.White)
                        }
                    },
                    dismissButton = {
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                            onClick = {
                                activity?.finish()
                            }) {
                            Text("Salir", fontSize = 20.sp, color = Color.White)
                        }
                    }
                )
            }
            // x= true
        }

    }

    reinicio()

    return x
}

//@Preview
@Composable
fun juego(
    accionesJugador1: MutableList<Int>,
    accionesJugador2: MutableList<Int>,
    casilla: Int,
    jugador: Int,
    jugadas: Int,
    agregarJugada: () -> Int,
    seleccionCasilla: () -> Unit,
    limpiar: () -> Unit,
    match: MutableList<Any>

) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        //Text(text = "Casilla actual: $casilla", textAlign = TextAlign.Center)
        //Text(text = "match: ${match.toString()}", textAlign = TextAlign.Center)

        pad(
            casilla,
            jugador,
            accionesJugador1,
            jugadas,
            agregarJugada,
            seleccionCasilla,
            limpiar,
            accionesJugador2
        ) {
        }
    }

}


@Composable
fun pad(
    casilla: Int,
    jugador: Int,
    accionesJugador1: MutableList<Int>,
    jugadas: Int,
    agregarJugada: () -> Int,
    seleccionCasilla: () -> Unit,
    limpiar: () -> Unit,
    accionesJugador2: MutableList<Int>,
    function: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .height(150.dp)
                .background(Color(0xFF00BCD4))
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
            ) {
                Text(text = "Gato", textAlign = TextAlign.Center, fontSize = 30.sp)
                Text(text = "Turno de jugador: $jugador", textAlign = TextAlign.Center)
                //Text(text = "Ganador: ", textAlign = TextAlign.Center)
                //Text(text = accionesJugador2.toString(), textAlign = TextAlign.Center)
                //Text(text = "Casilla actual: $casilla", textAlign = TextAlign.Center)
                Text(text = "jugadas hechas: $jugadas", textAlign = TextAlign.Center)
                //Text(text = accionesJugador1.toString(), textAlign = TextAlign.Center)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color(0xFF61FF3D)), horizontalArrangement = Arrangement.Center
        ) {
            /////////////////////////////////////////Divicion caja
            var valido1 = mutableListOf<Int>(1)
            var match1 = accionesJugador1.intersect(valido1)

            if (match1.isNotEmpty()) {

                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(100.dp)
                        .background(Color(0xFF07E3FF)), Alignment.Center

                ) {
                    contenido(cont = "X")
                }

            } else {
                match1 = accionesJugador2.intersect(valido1)

                if (match1.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(100.dp)
                            .background(Color(0xFF07E3FF)), Alignment.Center
                    ) {
                        contenido(cont = "O")
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(100.dp)
                            .background(Color(0xFF07E3FF))
                            .clickable() {
                                limpiar()
                                seleccionCasilla()
                                agregarJugada()
                            }
                    ) {
                        contenido(cont = "")
                    }

                }

            }
            /////////////////////////////////////////Divicion caja
            var valido2 = mutableListOf<Int>(2)
            var match2 = accionesJugador1.intersect(valido2)

            if (match2.isNotEmpty()) {

                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(100.dp)
                        .background(Color(0xFF07E3FF)), Alignment.Center
                ) {
                    contenido(cont = "X")
                }

            } else {
                match2 = accionesJugador2.intersect(valido2)

                if (match2.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(100.dp)
                            .background(Color(0xFF07E3FF)), Alignment.Center
                    ) {
                        contenido(cont = "O")
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(100.dp)
                            .background(Color(0xFF07E3FF))
                            .clickable() {
                                limpiar()
                                for (i in 0..1) {
                                    seleccionCasilla()
                                }
                                agregarJugada()
                            }
                    ) {
                        contenido(cont = "")
                    }

                }

            }
            /////////////////////////////////////////Divicion caja
            var valido3 = mutableListOf<Int>(3)
            var match3 = accionesJugador1.intersect(valido3)

            if (match3.isNotEmpty()) {

                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(100.dp)
                        .background(Color(0xFF07E3FF)), Alignment.Center
                ) {
                    contenido(cont = "X")
                }

            } else {
                match3 = accionesJugador2.intersect(valido3)

                if (match3.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(100.dp)
                            .background(Color(0xFF07E3FF)), Alignment.Center
                    ) {
                        contenido(cont = "O")
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(100.dp)
                            .background(Color(0xFF07E3FF))
                            .clickable() {
                                limpiar()
                                for (i in 0..2) {
                                    seleccionCasilla()
                                }
                                agregarJugada()
                            }
                    ) {
                        contenido(cont = "")
                    }

                }

            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color(0xFF61FF3D)), horizontalArrangement = Arrangement.Center
        ) {
            /////////////////////////////////////////Divicion caja
            var valido4 = mutableListOf<Int>(4)
            var match4 = accionesJugador1.intersect(valido4)

            if (match4.isNotEmpty()) {

                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(100.dp)
                        .background(Color(0xFF07E3FF)), Alignment.Center
                ) {
                    contenido(cont = "X")
                }

            } else {
                match4 = accionesJugador2.intersect(valido4)

                if (match4.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(100.dp)
                            .background(Color(0xFF07E3FF)), Alignment.Center
                    ) {
                        contenido(cont = "O")
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(100.dp)
                            .background(Color(0xFF07E3FF))
                            .clickable() {
                                limpiar()
                                for (i in 0..3) {
                                    seleccionCasilla()
                                }
                                agregarJugada()
                            }
                    ) {
                        contenido(cont = "")
                    }

                }

            }
            /////////////////////////////////////////Divicion caja
            var valido5 = mutableListOf<Int>(5)
            var match5 = accionesJugador1.intersect(valido5)

            if (match5.isNotEmpty()) {

                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(100.dp)
                        .background(Color(0xFF07E3FF)), Alignment.Center
                ) {
                    contenido(cont = "X")
                }

            } else {
                match5 = accionesJugador2.intersect(valido5)

                if (match5.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(100.dp)
                            .background(Color(0xFF07E3FF)), Alignment.Center
                    ) {
                        contenido(cont = "O")
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(100.dp)
                            .background(Color(0xFF07E3FF))
                            .clickable() {
                                limpiar()
                                for (i in 0..4) {
                                    seleccionCasilla()
                                }
                                agregarJugada()
                            }
                    ) {
                        Text(text = "", fontSize = 40.sp)
                    }

                }

            }
            /////////////////////////////////////////Divicion caja
            var valido6 = mutableListOf<Int>(6)
            var match6 = accionesJugador1.intersect(valido6)

            if (match6.isNotEmpty()) {

                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(100.dp)
                        .background(Color(0xFF07E3FF)), Alignment.Center
                ) {
                    contenido(cont = "X")
                }

            } else {
                match6 = accionesJugador2.intersect(valido6)

                if (match6.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(100.dp)
                            .background(Color(0xFF07E3FF)), Alignment.Center
                    ) {
                        contenido(cont = "O")
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(100.dp)
                            .background(Color(0xFF07E3FF))
                            .clickable() {
                                limpiar()
                                for (i in 0..5) {
                                    seleccionCasilla()
                                }
                                agregarJugada()
                            }
                    ) {
                        contenido(cont = "")
                    }

                }

            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color(0xFF61FF3D)), horizontalArrangement = Arrangement.Center
        ) {
            /////////////////////////////////////////Divicion caja
            var valido7 = mutableListOf<Int>(7)
            var match7 = accionesJugador1.intersect(valido7)

            if (match7.isNotEmpty()) {

                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(100.dp)
                        .background(Color(0xFF07E3FF)), Alignment.Center
                ) {
                    contenido(cont = "X")
                }

            } else {
                match7 = accionesJugador2.intersect(valido7)

                if (match7.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(100.dp)
                            .background(Color(0xFF07E3FF)), Alignment.Center
                    ) {
                        contenido(cont = "O")
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(100.dp)
                            .background(Color(0xFF07E3FF))
                            .clickable() {
                                limpiar()
                                for (i in 0..6) {
                                    seleccionCasilla()
                                }
                                agregarJugada()
                            }
                    ) {
                        contenido(cont = "")
                    }

                }

            }
            /////////////////////////////////////////Divicion caja
            var valido8 = mutableListOf<Int>(8)
            var match8 = accionesJugador1.intersect(valido8)

            if (match8.isNotEmpty()) {

                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(100.dp)
                        .background(Color(0xFF07E3FF)), Alignment.Center
                ) {
                    contenido(cont = "X")
                }

            } else {
                match8 = accionesJugador2.intersect(valido8)

                if (match8.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(100.dp)
                            .background(Color(0xFF07E3FF)), Alignment.Center
                    ) {
                        contenido(cont = "O")
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(100.dp)
                            .background(Color(0xFF07E3FF))
                            .clickable() {
                                limpiar()
                                for (i in 0..7) {
                                    seleccionCasilla()
                                }
                                agregarJugada()
                            }
                    ) {
                        contenido(cont = "")
                    }

                }

            }
            /////////////////////////////////////////Divicion caja
            var valido9 = mutableListOf<Int>(9)
            var match9 = accionesJugador1.intersect(valido9)

            if (match9.isNotEmpty()) {

                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .size(100.dp)
                        .background(Color(0xFF07E3FF)), Alignment.Center
                ) {
                    contenido(cont = "X")
                }

            } else {
                match9 = accionesJugador2.intersect(valido9)

                if (match9.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(100.dp)
                            .background(Color(0xFF07E3FF)), Alignment.Center
                    ) {
                        contenido(cont = "O")
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .size(100.dp)
                            .background(Color(0xFF07E3FF))
                            .clickable() {
                                limpiar()
                                for (i in 0..8) {
                                    seleccionCasilla()
                                }
                                agregarJugada()
                            }
                    ) {
                        contenido(cont = "")
                    }

                }

            }
        }

    }
}

@Composable
fun contenido(cont: String) {
    Text(text = cont, fontSize = 40.sp, textAlign = TextAlign.Center)
}


package Examen.examenTec

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import Examen.examenTec.ui.theme.ExamenTheme
import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var tabla by remember {mutableStateOf(mutableListOf<MutableList<String>>(mutableListOf<String>("","",""),mutableListOf<String>("","",""),mutableListOf<String>("","","")))}
            var jugador by remember { mutableStateOf( true)}
            var ganador by remember { mutableStateOf("")}

            ExamenTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Iniciar()
                    Gato(tabla, jugador) {
                        jugador = !jugador
                        ganador = ObtenerGanador(tabla)
                    }
                    if (ganador != ""){
                        Fin(ganador)
                        tabla = mutableListOf<MutableList<String>>(mutableListOf<String>("","",""),mutableListOf<String>("","",""),mutableListOf<String>("","",""))
                        jugador = true
                    }
                }
            }
        }
    }
}

@Composable
fun Iniciar() {
    MaterialTheme {
        Column {
            val openDialog = remember { mutableStateOf(true)  }
            if (openDialog.value){
                AlertDialog(
                    modifier = Modifier.fillMaxSize(),
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    title = {
                        Text(text = "GATO",fontSize = 35.sp,fontWeight = FontWeight(600))
                    },
                    confirmButton = {
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                            onClick = {
                                openDialog.value = false
                            }) {
                            Text("Iniciar",fontSize = 20.sp, color = Color.White)
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun Gato(tabla: MutableList<MutableList<String>> , jugador: Boolean, cambiojugador: () -> Unit){

    Column(){
        Text(text = "Juego Del Gato", fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 45.sp, modifier = Modifier
            .align(CenterHorizontally)
            .padding(20.dp)
        )
        Row(modifier = Modifier.align(CenterHorizontally)){
            (Cuadro(0,0,tabla,jugador, cambiojugador))
            (Cuadro(0,1,tabla,jugador, cambiojugador))
            (Cuadro(0,2,tabla,jugador, cambiojugador))
        }
        Row(modifier = Modifier.align(CenterHorizontally))
        {
            (Cuadro(1,0,tabla,jugador, cambiojugador))
            (Cuadro(1,1,tabla,jugador, cambiojugador))
            (Cuadro(1,2,tabla,jugador, cambiojugador))
        }
        Row(modifier = Modifier.align(CenterHorizontally)){
            (Cuadro(2,0,tabla,jugador, cambiojugador))
            (Cuadro(2,1,tabla,jugador, cambiojugador))
            (Cuadro(2,2,tabla,jugador, cambiojugador))
        }

    }
}

@Composable
fun Cuadro(x:Int, y:Int, tabla: MutableList<MutableList<String>>, jugador: Boolean, cambiojugador:() -> Unit) :String {
    var buttonState by remember { mutableStateOf("") }
    Button(
        onClick = {
            // poner en la mariz en la posicion x,y el valor de button state
            if(tabla[x][y] == "") {
                tabla[x][y] = if (jugador) "x" else "o"
                cambiojugador()
            }
        },
        modifier = Modifier
            .padding(5.dp)
    ) {
        Text(text = tabla[x][y])
    }
    return buttonState
}

fun ObtenerGanador(gameState: MutableList<MutableList<String>>): String {
    for (row in gameState) {
        if (row[0] != "" && row[0] == row[1] && row[1] == row[2]) {
            return row[0]
        }
    }

    for (col in 0..2) {
        if (gameState[0][col] != "" && gameState[0][col] == gameState[1][col] && gameState[1][col] == gameState[2][col]) {
            return gameState[0][col]
        }
    }

    if (gameState[0][0] != "" && gameState[0][0] == gameState[1][1] && gameState[1][1] == gameState[2][2]) {
        return gameState[0][0]
    }
    if (gameState[0][2] != "" && gameState[0][2] == gameState[1][1] && gameState[1][1] == gameState[2][0]) {
        return gameState[0][2]
    }

    for (row in gameState) {
        for (cell in row) {
            if (cell == "") {
                return ""
            }
        }
    }
    return "Empate"
}

@Composable
fun Fin(ganador : String) {
    val activity = (LocalContext.current as? Activity)
    MaterialTheme {
        Column {
            val openDialog = remember { mutableStateOf(true)  }
            if (openDialog.value){
                AlertDialog(
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    title = {
                        Text(text = "¡Fin del juego!",fontSize = 35.sp,fontWeight = FontWeight(600))
                    },
                    text = {
                        Text("\nGanador: "+ganador,fontSize = 20.sp, color = Color.Black)
                    },
                    confirmButton = {
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                            onClick = {
                                openDialog.value = false
                            }) {
                            Text("Reiniciar",fontSize = 20.sp, color = Color.White)
                        }
                    },
                    dismissButton = {
                        Button(
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                            onClick = {
                                activity?.finish()
                            }) {
                            Text("Salir",fontSize = 20.sp, color = Color.White)
                        }
                    }
                )
            }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun PreviewGato(){
    var tabla by remember {mutableStateOf(mutableListOf<MutableList<String>>(mutableListOf<String>("","",""),mutableListOf<String>("","",""),mutableListOf<String>("","","")))}
    var jugador by remember { mutableStateOf( true)}
    var ganador by remember { mutableStateOf("")}
    Gato(tabla, jugador) {
        jugador = !jugador
        ganador = ObtenerGanador(tabla)
    }
}
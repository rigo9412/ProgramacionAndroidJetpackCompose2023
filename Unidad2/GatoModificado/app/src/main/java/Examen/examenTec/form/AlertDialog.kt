package Examen.examenTec.form

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import Examen.examenTec.R
import android.app.Activity
import androidx.compose.ui.platform.LocalContext

@Composable
fun Iniciar() {
    MaterialTheme {
        Column {
            val openDialog = remember { mutableStateOf(true)  }
            if (openDialog.value){
                AlertDialog(
                    modifier = Modifier.fillMaxSize().align(Alignment.CenterHorizontally).padding(15.dp),
                    onDismissRequest = {
                        openDialog.value = false
                    },
                    title = {
                        Column {
                            Text(text = "Juego del Gato!",fontSize = 45.sp,fontWeight = FontWeight(600),
                            )
                            Image(
                                painter = painterResource(id = R.drawable.tictactoe),
                                contentDescription = "Gato Logo",
                                contentScale = ContentScale.Fit,
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(200.dp)
                                    .align(Alignment.CenterHorizontally)
                            )
                        }
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
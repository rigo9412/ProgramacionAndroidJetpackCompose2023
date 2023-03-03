package Examen.examenTec

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import Examen.examenTec.ui.theme.ExamenTheme
import android.app.Activity
import android.app.AlertDialog
import android.app.GameState
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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

            val matriz = Array(3) { arrayOfNulls<String>(3) }
            val interactionSource = remember { MutableInteractionSource() }
            //val isPressed by interactionSource.collectIsPressedAsState()

            ExamenTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Iniciar()
                    Gato()
                }
            }
        }
    }
}

@Composable
fun Iniciar() {
    val activity = (LocalContext.current as? Activity)
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
fun Gato(){
    var x = mutableListOf<String>()
    Column(){
        Text(text = "Juego Del Gato", fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 45.sp, modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .padding(20.dp)
        )
        Row(modifier = Modifier.align(CenterHorizontally)){
            x.add(Cuadro())
            x.add(Cuadro())
            x.add(Cuadro())
        }
        Row(modifier = Modifier.align(CenterHorizontally))
        {
            x.add(Cuadro())
            x.add(Cuadro())
            x.add(Cuadro())
        }
        Row(modifier = Modifier.align(CenterHorizontally)){
            x.add(Cuadro())
            x.add(Cuadro())
            x.add(Cuadro())
        }

    }
}
var x = 1

@Composable
fun Cuadro() :String {
    var buttonState by remember { mutableStateOf("") }
    Button(
        onClick = {
            buttonState = if (buttonState == "" && (x%2!=0)) "X" else "0"
        },
        modifier = Modifier
            .padding(5.dp)
    ) {
        Text(text = buttonState)
        x++
    }
    return buttonState
}


@Preview (showBackground = true)
@Composable
fun PreviewGato(){
    Gato()
}
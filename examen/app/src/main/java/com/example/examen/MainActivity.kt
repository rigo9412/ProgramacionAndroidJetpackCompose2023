package com.example.examen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.examen.ui.theme.ExamenTheme
var isPlaying = mutableStateOf(false)
var startGame = mutableStateOf("Inicio")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {

            ExamenTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Gato()
                }

            }

        }
    }
}

@Composable
fun Gato() {
    val board = remember { mutableStateListOf("", "", "", "", "", "", "", "", "") }
    var turn by remember { mutableStateOf("X") }
    var winner by remember { mutableStateOf("") }
    var draw by remember { mutableStateOf("")}
    var moves by remember { mutableStateOf(0) }


    fun CheckWinner() {
        val combinations = arrayOf(
            intArrayOf(0, 1, 2),
            intArrayOf(3, 4, 5),
            intArrayOf(6, 7, 8),
            intArrayOf(0, 3, 6),
            intArrayOf(1, 4, 7),
            intArrayOf(2, 5, 8),
            intArrayOf(0, 4, 8),
            intArrayOf(2, 4, 6)
        )
        for (comb in combinations) {
            if (board[comb[0]].isNotEmpty() && board[comb[0]] == board[comb[1]] && board[comb[1]] == board[comb[2]]) {
                winner = board[comb[0]]
            }
        }
        if (moves == 9 && winner.isEmpty()) {
            draw = "Empate"
        }
    }

    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Gato", fontSize = 48.sp)
        Spacer(modifier = Modifier.height(32.dp))
        LazyVerticalGrid( columns = GridCells.Adaptive(minSize = 128.dp), contentPadding = PaddingValues(8.dp)) {
            items(9) { index ->
                Box(
                    Modifier
                        .width(100.dp)
                        .height(100.dp)
                        .background(color = Color.Gray)
                        .border(width = 1.dp, color = Color.Black)
                        .clickable {
                            if (winner.isEmpty() && board[index].isEmpty()) {
                                board[index] = turn
                                turn = if (turn == "X") "O" else "X"
                                moves++ //para contar los movs para el empate
                                CheckWinner()
                            }
                        }
                ) {
                    Text(
                        text = board[index],
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        if (winner.isNotEmpty()) {
            Text(text = "El ganador es: $winner", fontSize = 24.sp)
        } else {
            if(draw =="Empate"){
                Text(text = "Es un: $draw", fontSize = 24.sp)
            }
            else {
                Text(text = "Turno de Jugador: $turn", fontSize = 24.sp)
            }
        }

    }
}


@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    ExamenTheme {
        Gato()
        if (!isPlaying.value){
            Button(modifier = Modifier.fillMaxSize(),  onClick = { isPlaying.value = true; startGame.value = ""},
                colors = ButtonDefaults.buttonColors(disabledBackgroundColor = Color.Transparent)) {
                Text(text = startGame.value , fontSize = 50.sp)
            }
        }
    }
}
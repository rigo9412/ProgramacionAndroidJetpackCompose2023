package com.example.myapplication.ui.gato.ui

import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Preview
@Composable
fun MyPreview(){
    gatoGame(modifier = Modifier, viewModel = GatoGameViewModel())
}

@Composable
fun gatoGameScreen(viewModel: GatoGameViewModel){
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ){
        gatoGame(modifier = Modifier.align(Alignment.Center), viewModel = GatoGameViewModel())
    }

}

@Composable
fun gatoGame(modifier: Modifier, viewModel: GatoGameViewModel){
    val playing : Boolean by viewModel.playing.observeAsState(false)
    val currentPlayer : String by viewModel.currentPlayer.observeAsState("")
    val ended : Boolean by viewModel.ended.observeAsState(false)
    val winner : String by viewModel.winner.observeAsState("")


    Column(modifier = modifier){
        Text(text = "Gato", textAlign = TextAlign.Center, color = Color.White, fontSize =30.sp, fontWeight= FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(4.dp))
        if(!playing && !ended) {
            botonEmpezar { viewModel.gameStart() }
        }
        else {
            headerPlaying()
            showCurrentPlayer(currentPlayer = currentPlayer)
            gatoGrid(viewModel, currentPlayer,ended)
            showWinner(winner = winner)
            if(ended){
                botonReset {
                    viewModel.resetGame()
                }
            }
        }
    }
}

@Composable
fun showCurrentPlayer(currentPlayer: String){
    Text(text = "Turno de $currentPlayer")
}

@Composable
fun showWinner(winner: String){
    var txt = ""
    if(winner == "Empate"){
        txt = "Es un empate"
    }
    else{
        txt = "El ganador es $winner"
    }
    txt = if(winner == "") "" else txt
    Text(text = txt)

}

@Composable
fun headerPlaying(){
    Text(text = "Juego de gato iniciado")
}

@Composable
fun headerPlayer(player: String){
    Text(text = "Le toca jugar a: $player")
}

@Composable
fun botonReset(reset:() -> Unit){
    Button(onClick = {reset()},
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors= ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFFF4303),
            disabledBackgroundColor = Color(0xFFF78058),
            contentColor = Color.White,
            disabledContentColor = Color.White
        )) {
        Text(text = "Reiniciar")
    }
}

@Composable
fun botonEmpezar(empezarJuego:() -> Unit){
    Button(onClick = {empezarJuego()},
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors= ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFFF4303),
            disabledBackgroundColor = Color(0xFFF78058),
            contentColor = Color.White,
            disabledContentColor = Color.White
        )) {
        Text(text = "Empezar Juego")
    }
}

@Composable
fun gatoGrid(gatoGameViewModel: GatoGameViewModel,currentPlayer: String, ended : Boolean){

    val text1 : String by gatoGameViewModel.isSelected1.observeAsState("")
    val text2 : String by gatoGameViewModel.isSelected2.observeAsState("")
    val text3 : String by gatoGameViewModel.isSelected3.observeAsState("")
    val text4 : String by gatoGameViewModel.isSelected4.observeAsState("")
    val text5 : String by gatoGameViewModel.isSelected5.observeAsState("")
    val text6 : String by gatoGameViewModel.isSelected6.observeAsState("")
    val text7 : String by gatoGameViewModel.isSelected7.observeAsState("")
    val text8 : String by gatoGameViewModel.isSelected8.observeAsState("")
    val text9 : String by gatoGameViewModel.isSelected9.observeAsState("")

    Row(){
        Column(){
            gatoBlock(text = text1, num =1,viewModel = gatoGameViewModel,ended)
            gatoBlock(text = text2, num =2,viewModel =gatoGameViewModel,ended)
            gatoBlock(text = text3,num =3,viewModel =gatoGameViewModel,ended)
        }
        Column(){
            gatoBlock(text = text4,num =4,viewModel =gatoGameViewModel,ended)
            gatoBlock(text = text5, num =5,viewModel =gatoGameViewModel,ended)
            gatoBlock(text = text6, num = 6,viewModel =gatoGameViewModel,ended)
        }
        Column(){
            gatoBlock(text = text7, num = 7,viewModel =gatoGameViewModel,ended)
            gatoBlock(text = text8, num = 8,viewModel =gatoGameViewModel,ended)
            gatoBlock(text = text9, num = 9,viewModel =gatoGameViewModel,ended)
        }
    }

}

@Composable
fun gatoBlock(text : String, num : Int,viewModel: GatoGameViewModel,isEnabled : Boolean){
    Box(
        modifier = Modifier
            .padding(5.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(if (text == "") Color.Gray else Color.White)
            .width(115.dp)
            .height(115.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                if (text == "" && !isEnabled ) {
                    viewModel.onSelectChange(num)
                }
            },
        contentAlignment = Alignment.Center
    ){
        Text(text = text, textAlign = TextAlign.Center, color = Color.Black, fontSize =20.sp, fontWeight= FontWeight.Bold)
    }
}

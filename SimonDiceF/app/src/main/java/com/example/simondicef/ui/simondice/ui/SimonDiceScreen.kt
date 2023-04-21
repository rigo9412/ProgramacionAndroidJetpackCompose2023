package com.example.simondicef.ui.simondice.ui

import android.content.Context
import android.graphics.Paint.Align
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.SoundPool
import android.provider.MediaStore.Audio.Media
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simondicef.R
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.example.simondicef.leaderboard.ui.LeaderboardViewModel
import kotlinx.coroutines.launch

//@Composable
//fun MyPreview(){
//    simonDiceScreen(viewModel = SimonDiceViewModel())
//}

var b1Color = Color.Blue
var b2Color = Color.Green
var b3Color = Color.Red
var b4Color = Color(0xFFDFD846)

var b1ColorOn = Color(0xFFB1B8FF)
var b2ColorOn = Color(0xFFC7FFBD)
var b3ColorOn = Color(0xFFFFBEBE)
var b4ColorOn = Color(0xFFFFF191)

@Composable
fun simonDiceScreen(viewModel: SimonDiceViewModel,leaderboard: LeaderboardViewModel){

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ){
        simonDice(Modifier.align(Alignment.Center),viewModel, leaderboard = leaderboard)
    }
}

@Composable
fun simonDice(modifier: Modifier, viewModel: SimonDiceViewModel,leaderboard: LeaderboardViewModel) {
    val score : Int by viewModel.score.observeAsState(0)
    val lastScore : Int by viewModel.lastScore.observeAsState(0)
    val started : Boolean by viewModel.started.observeAsState(false)
    val nivel : Int by viewModel.nivel.observeAsState(0)
    val lost : Boolean by viewModel.lost.observeAsState(false)

    var returnB = leaderboard.returnB.collectAsState(true)

    Log.d("SCORE","$score")
    Log.d("LASTSCORE","$lastScore")
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = modifier) {
        if (!lost && returnB.value) {
            Text(
                text = "Simon Dice",
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            HeaderNivel(nivel)
            HeaderScore(score)
            Spacer(modifier = Modifier.padding(4.dp))
            simonDiceBlocks(Modifier.align(Alignment.CenterHorizontally), viewModel = viewModel)
            Spacer(modifier = Modifier.padding(5.dp))
            botonEmpezar(started) { coroutineScope.launch { viewModel.empezarJuego() } }
        }
        else{
            if(returnB.value){
                leaderboard.goBack(false)
            }
            else{
                viewModel.changeLost(false)
            }
            resultView(viewModel = leaderboard, score = lastScore)
        }
    }
}


@Composable
fun botonEmpezar(started : Boolean, empezarJuego:() -> Unit){
    Button(onClick = { empezarJuego() },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors= ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFFF4303),
            disabledBackgroundColor = Color(0xFFF78058),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ),
        enabled = !started) {
        Text(text = "Empezar Juego")
    }
}

@Composable
fun HeaderNivel(nivel : Int) {
    Text(text="Nivel: $nivel")
}

@Composable
fun HeaderScore(score : Int) {
    Text(text="Puntaje: $score")
}

@Composable
fun simonDiceBlocks(modifier : Modifier, viewModel: SimonDiceViewModel){
    val context = LocalContext.current

    val isSelected1 : Boolean by viewModel.isSelected1.observeAsState(false)
    val isSelected2 : Boolean by viewModel.isSelected2.observeAsState(false)
    val isSelected3 : Boolean by viewModel.isSelected3.observeAsState(false)
    val isSelected4 : Boolean by viewModel.isSelected4.observeAsState(false)
    val isEnabled : Boolean by viewModel.isInputEnabled.observeAsState(false)

    val b1Audio : MediaPlayer by remember{ mutableStateOf(MediaPlayer.create(context,R.raw.sfx1))}
    val b2Audio : MediaPlayer by remember{ mutableStateOf(MediaPlayer.create(context,R.raw.sfx2))}
    val b3Audio : MediaPlayer by remember{ mutableStateOf(MediaPlayer.create(context,R.raw.sfx3))}
    val b4Audio : MediaPlayer by remember{ mutableStateOf(MediaPlayer.create(context,R.raw.sfx4))}

    Row(modifier = modifier
        .clip(RoundedCornerShape(10.dp))
        .background(if (!isEnabled) Color(0xFF777777) else MaterialTheme.colors.background)){
        Column() {
            ColorBlock(backColor = b1Color, b1ColorOn, text = "Azul",1, isSelected = isSelected1,isEnabled,b1Audio, viewModel, shape = QuarterCircleShape,0f)
            ColorBlock(backColor = b2Color, b2ColorOn,text = "Verde",2,isSelected = isSelected2,isEnabled,b2Audio, viewModel, shape = QuarterCircleShape,-90f)
        }
        Column() {
            ColorBlock(backColor = b3Color, b3ColorOn,text = "Rojo",3,isSelected = isSelected3,isEnabled,b3Audio, viewModel, shape =  QuarterCircleShape,90f)
            ColorBlock(backColor = b4Color, b4ColorOn,text = "Amarillo",4,isSelected= isSelected4,isEnabled,b4Audio, viewModel, shape =  QuarterCircleShape,180f)
        }
    }
}

@Composable
fun ColorBlock(backColor : Color,
               colorOn : Color,
               text : String,
               num : Int,
               isSelected : Boolean,
               isEnabled : Boolean,
               audio : MediaPlayer,
               viewModel: SimonDiceViewModel,
               shape : Shape,
               rotation : Float){
    if(isSelected){
        playSFX(audio)
    }
    val coroutineScope = rememberCoroutineScope()
    Box(modifier = Modifier.rotate(rotation)) {
        Box(
            modifier = Modifier
                .padding(5.dp)
                .clip(shape)
                .background(if (isSelected) colorOn else backColor)
                .size(150.dp)
                .clickable {
                    if (isEnabled) {
                        coroutineScope.launch { viewModel.selectShow(num) }
                        coroutineScope.launch { viewModel.onSelectChange(num) }
                        playSFX(audio)
                    }
                },
            contentAlignment = Alignment.Center
        ) {
        }
    }
}

@Preview
@Composable
fun PreviewColorBlocks(){
    Row(){
        Column() {
            PreviewColorBlock(backColor = b1Color, text = "Azul", QuarterCircleShape,0f)
            PreviewColorBlock(backColor = b2Color, text = "Verde", QuarterCircleShape,-90f)
        }
        Column() {
            PreviewColorBlock(backColor = b3Color, text = "Rojo", QuarterCircleShape,90f)
            PreviewColorBlock(backColor = b4Color, text = "Amarillo", QuarterCircleShape,180f)
        }
    }
}

@Composable
fun PreviewColorBlock(backColor : Color,
               text : String, shape : Shape,rotation : Float){
    Box(modifier = Modifier.rotate(rotation)) {
        Box(
            modifier = Modifier
                .padding(10.dp)
                .clip(shape)
                .background(backColor)
                .size(150.dp)
                .clickable {
                },
            contentAlignment = Alignment.Center
        ) {
        }
    }
}


private val TriangleShape = GenericShape {size, _ ->
    // 1)
    moveTo(size.width , 0f)

    // 2)
    lineTo(size.width, size.height)

    // 3)
    lineTo(0f, size.height)
}

private val QuarterCircleShape = GenericShape { size, _ ->
    moveTo(size.width,0f)
    arcTo(
        rect = Rect(0f, 0f, size.width*2, size.height*2),
        startAngleDegrees = -90f,
        sweepAngleDegrees = -90f,
        forceMoveTo = false
    )
    lineTo(0f,size.height)
    lineTo(size.width,size.height)
}

fun playSFX(mp : MediaPlayer){
    if(!mp.isPlaying){
        mp.start()
    }
    else{
        mp.pause()
        mp.seekTo(0)
        mp.start()
    }
}
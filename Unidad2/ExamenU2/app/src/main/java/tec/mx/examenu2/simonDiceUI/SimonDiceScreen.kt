package tec.mx.examenu2.simonDiceUI

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import tec.mx.examenu2.R
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import tec.mx.examenu2.ui.theme.*
import kotlinx.coroutines.launch


@Composable
fun PantallaInicio(viewModel: SimonDiceViewModel){
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)
    ){
        simonDice(Modifier.align(Alignment.Center),viewModel)
    }
}

@Composable
fun simonDice(modifier: Modifier, viewModel: SimonDiceViewModel) {
    val puntaje : Int by viewModel.score.observeAsState(0)
    val inicializacion : Boolean by viewModel.started.observeAsState(false)
    val nivel : Int by viewModel.nivel.observeAsState(0)
    val coroutineScope = rememberCoroutineScope()

    var nombreIngresado by remember { mutableStateOf("") }

    Column(modifier = modifier){
        OutlinedTextField(
            value = nombreIngresado,
            onValueChange = { nombreIngresado = it },
            label = { Text("Nombre") }
        )
        Text(text = "Simon Say's", textAlign = TextAlign.Center, color = Color.Black, fontSize =40.sp, fontWeight= FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally))
        HeaderNivel(nivel)
        HeaderScore(puntaje)
        Spacer(modifier = Modifier.padding(4.dp))
        simonDiceBlocks(Modifier.align(Alignment.CenterHorizontally),viewModel = viewModel)
        Spacer(modifier = Modifier.padding(5.dp))
        btnComenzar(inicializacion) {coroutineScope.launch {viewModel.empezarJuego(nombreIngresado)}}
        HighScoresList(viewModel._highScores)
    }
}

@Composable
fun btnComenzar(started : Boolean, empezarJuego:() -> Unit){
    Button(onClick = { empezarJuego() },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        colors= ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF090909),
            contentColor = Color.White,
            disabledContentColor = Color.White
        ),
        enabled = !started) {
        Text(text = "Iniciar")
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

    val b1Audio : MediaPlayer by remember{ mutableStateOf(MediaPlayer.create(context,R.raw.yoshi))}
    val b2Audio : MediaPlayer by remember{ mutableStateOf(MediaPlayer.create(context,R.raw.poyo))}
    val b3Audio : MediaPlayer by remember{ mutableStateOf(MediaPlayer.create(context,R.raw.hey))}
    val b4Audio : MediaPlayer by remember{ mutableStateOf(MediaPlayer.create(context,R.raw.coin))}

    Row(modifier = modifier.clip(RoundedCornerShape(10.dp))
        .background(if(!isEnabled) Color(0xFFFFFFFF) else MaterialTheme.colors.background)){
        Column() {
            ColorBlock(backColor = btnAzul1, btnAzul, text = "Azul",1, isSelected = isSelected1,isEnabled,b1Audio, viewModel)
            ColorBlock(backColor = btnVerde1, btnVerde,text = "Verde",2,isSelected = isSelected2,isEnabled,b2Audio, viewModel)
        }
        Column() {
            ColorBlock(backColor = btnRojo1, btnRojo,text = "Rojo",3,isSelected = isSelected3,isEnabled,b3Audio, viewModel)
            ColorBlock(backColor = btnAmarillo1, btnAmarillo,text = "Amarillo",4,isSelected= isSelected4,isEnabled,b4Audio, viewModel)
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
               viewModel: SimonDiceViewModel)
{
    if(isSelected){
        playSFX(audio)
    }
    val coroutineScope = rememberCoroutineScope()
    Box {
        Box(
            modifier = Modifier
                .padding(5.dp)
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

@Composable
fun HighScoresList(scores: List<Score>) {
    Text("\nPuntajes")
    LazyColumn {
        items(scores) { score ->
            Text("${score.name}: ${score.score}")
        }
    }
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
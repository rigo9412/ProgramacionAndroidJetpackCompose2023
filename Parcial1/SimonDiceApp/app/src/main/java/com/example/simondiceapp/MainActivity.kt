package com.example.simondiceapp

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simondiceapp.domain.models.Action
import com.example.simondiceapp.domain.models.Game
import com.example.simondiceapp.domain.models.Player
import com.example.simondiceapp.ui.theme.*
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    private val game = Game()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val blueAudio: MediaPlayer by remember { mutableStateOf(MediaPlayer.create(this, R.raw.blue)) }
            val redAudio: MediaPlayer by remember { mutableStateOf(MediaPlayer.create(this, R.raw.red)) }
            val yellowAudio: MediaPlayer by remember { mutableStateOf(MediaPlayer.create(this, R.raw.yellow)) }
            val greenAudio: MediaPlayer by remember { mutableStateOf(MediaPlayer.create(this, R.raw.green)) }
            val themeAudio: MediaPlayer by remember { mutableStateOf(MediaPlayer.create(this, R.raw.themesoundtrack)) }
        var currentActionSimonIndexState by remember{ mutableStateOf(game.currentActionSimonIndex)}
        var resultsxState by remember { mutableStateOf<Player?>(null)}
        var currentActionPlayer by remember{ mutableStateOf(Action.NO_ACTION)}
        var currentActionOn by remember{ mutableStateOf(false)}
        var startGameState by remember{ mutableStateOf(game.started)}
        var startPlayState by remember{ mutableStateOf(game.endSpeak)}    
            
            SimonDiceAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LaunchedEffect(startGameState){
                        if(!startGameState){
                            playTheme(themeAudio)
                        }

                    }
                    LaunchedEffect(currentActionSimonIndexState){
                        val action = game.getCurrentAction()
                        println("${action.toString()} = ${game.currentActionSimonIndex}")
                        if (startGameState && !currentActionOn && !game.endSpeak){
                            when(action){
                                Action.PRESS_GREEN_BUTTON -> playAudio(greenAudio)
                                Action.PRESS_RED_BUTTON -> playAudio(redAudio)
                                Action.PRESS_YELLOW_BUTTON -> playAudio(yellowAudio)
                                Action.PRESS_BLUE_BUTTON ->  playAudio(blueAudio)
                                else -> {
                                    //no suena
                                }
                            }
                        }
                        currentActionOn = true
                        delay(1000)
                        currentActionOn = false
                        game.moveToNextAction()
                        currentActionSimonIndexState = game.currentActionSimonIndex
                    }
                    startPlayState = game.endSpeak
                }
                LaunchedEffect(currentActionPlayer){
                    if (startGameState && game.endSpeak && currentActionPlayer != Action.NO_ACTION && !currentActionOn) {
                        when (currentActionPlayer) {
                            Action.PRESS_GREEN_BUTTON ->  playAudio(greenAudio)
                            Action.PRESS_RED_BUTTON -> playAudio(redAudio)
                            Action.PRESS_YELLOW_BUTTON -> playAudio(yellowAudio)
                            Action.PRESS_BLUE_BUTTON ->  playAudio(blueAudio)

                            else -> {
                                //NO SUENA
                            }
                        }
                        currentActionOn = true
                        delay(300)
                        currentActionOn = false

                        if(!game.validateAction(currentActionPlayer)){
                            println("END GAME")
                            resultsxState = game.end("dummy")
                            startGameState = game.started
                        }
                        currentActionSimonIndexState = game.currentActionSimonIndex

                        when(currentActionPlayer){
                            Action.PRESS_GREEN_BUTTON ->  playAudio(greenAudio)
                            Action.PRESS_RED_BUTTON -> playAudio(redAudio)
                            Action.PRESS_YELLOW_BUTTON -> playAudio(yellowAudio)
                            Action.PRESS_BLUE_BUTTON ->  playAudio(blueAudio)
                            else->{
                                //no suena
                            }
                        }

                        currentActionPlayer = Action.NO_ACTION
                }
            }
                Column(
                    modifier = Modifier.fillMaxSize()
                )
                    {
                        Spacer(modifier = Modifier
                            .height(20.dp)
                            .fillMaxWidth())
                        SimonGame(
                            game = game,
                            results = resultsxState,
                            actionPlayer = currentActionPlayer,
                            onCurrent = currentActionOn
                        ){
                            currentActionPlayer = it
                        }
                    }
                    Spacer(
                        modifier = Modifier
                            .height(14.dp)
                            .fillMaxWidth() )

                    if(!game.started){
                        CustomButton(text = "INICIAR JUEGO", startGameState, onClick = {
                            game.start()
                            currentActionSimonIndexState = game.currentActionSimonIndex
                            startGameState = game.started
                            resultsxState = null
                        } )
                    }

                }
        }
            
    }
}

private fun  playTheme(mp:MediaPlayer){
    mp.start()
}
private fun playAudio(mp:MediaPlayer){
    println("AUDIO ${mp.isPlaying}")
    if(!mp.isPlaying) {
        mp.start()
    }else{
        mp.pause();
        mp.seekTo(0);
        mp.start()
    }
}

@Composable
fun SimonGame(game:Game,results:Player?, actionPlayer: Action, onCurrent:Boolean, onClick:(action:Action)->Unit){
    Column(
        modifier = Modifier.background(Color.Black),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Status(game,results)
        Score(game,results)
        Pad(if (game.endSpeak) actionPlayer else game.getCurrentAction(),
        onCurrent,
            enablePlay = game.endSpeak,
            onClick
            )
    }
}

@Composable
fun Status(game:Game, results: Player?){
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        TitleShadow()
        if(game.started && results == null){
            Status(status = "JUEGO INICIADO")
        }else if(results!=null){
            Status(status = "JUEGO TERMINADO")
        }
    }
}
@Composable
fun Score(game: Game, player: Player?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (player != null) {
            Text(
                text = "SCORE: ${player.score}",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight(900)
            )
            Text(
                text = "LEVEL: ${player.level}",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight(900)
            )
        } else {
            Text(
                text = "SCORE: ${game.score}",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight(900)
            )
            Text(
                text = "LEVEL: ${game.level}",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight(900)
            )
        }

    }
}

@Composable
fun Pad(
    action: Action?, onCurrent: Boolean, enablePlay: Boolean, onClick: (action: Action) -> Unit
) {


    Column(
        modifier = Modifier.fillMaxWidth()

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            ButtonAction(
                on = action == Action.PRESS_GREEN_BUTTON && onCurrent,
                colorOn = GreenOn,
                colorOff = GreenOff,
                colorText = "Verde",
                enablePlay = enablePlay,
                rotate = 135f,
                action = Action.PRESS_GREEN_BUTTON,
                onClick = onClick
            )
            ButtonAction(
                on = action == Action.PRESS_RED_BUTTON && onCurrent,
                colorOn = RedOn,
                colorOff = RedOff,
                colorText = "Rojo",
                rotate = -135f,
                enablePlay = enablePlay,
                action = Action.PRESS_RED_BUTTON,
                onClick = onClick

            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            ButtonAction(
                on = action == Action.PRESS_BLUE_BUTTON && onCurrent,
                colorOn = BlueOn,
                colorOff = BlueOff,
                colorText = "Azul",
                enablePlay = enablePlay,
                rotate = 45f,
                action = Action.PRESS_BLUE_BUTTON,
                onClick = onClick
            )
            ButtonAction(
                on = action == Action.PRESS_YELLOW_BUTTON && onCurrent,
                colorOn = YellowOn,
                colorOff = YellowOff,
                colorText = "Amarillo",
                enablePlay = enablePlay,
                rotate = -45f,
                action = Action.PRESS_YELLOW_BUTTON,
                onClick = onClick
            )
        }
    }
}

@Composable
fun ButtonAction(
    on: Boolean,
    colorOn: Color,
    colorOff: Color,
    colorText: String,
    enablePlay: Boolean,
    rotate: Float,
    action: Action,
    onClick: (Action) -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(

        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
            .padding(4.dp)
            .rotate(rotate)
            .border(4.dp, colorOn, shape = buttonShape)
//            .background(if (on) colorOn else colorOff)
            .shadow(
                elevation = 30.dp,
                shape = buttonShape,
                ambientColor = Color.White,
                spotColor = Color.White
            )
            .background(
                if (on) colorOn else colorOff, buttonShape
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = !on && enablePlay
            ) {
                onClick(action)
            }, contentAlignment = Alignment.Center
    ) {
//        Text(text = colorText, fontWeight = FontWeight(900), color = Color.White)
    }
}
@Composable
fun CustomButton(text: String, startGameState: Boolean, onClick: () -> Unit) {
    TextButton(modifier = Modifier
        .padding(10.dp)
        .height(80.dp)
        .fillMaxWidth(),
        border = BorderStroke(
            width = 2.dp, brush = Brush.horizontalGradient(
                listOf(
                    Color.Cyan, Color.Yellow
                )
            )
        ),

        enabled = !startGameState,
        onClick = { onClick() }) {
        Text(text = text, color = Color.White, fontSize = 28.sp)
    }
}
@Composable
fun TitleShadow() {
    val offset = Offset(5.0f, 10.0f)
    Text(
        text = "SIMON GAME", color = Color.Yellow, style = TextStyle(
            fontSize = 60.sp, shadow = Shadow(
                color = Color.Cyan, offset = offset, blurRadius = 6f
            )
        )
    )
}
@Composable
fun Status(status: String) {
    Text(
        modifier = Modifier.background(Color.Cyan),
        text = status,
        color = Color.Red,
        fontSize = 24.sp

    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SimonDiceAppTheme {
        val dummyAction = Action.PRESS_RED_BUTTON
        val game = Game()
        SimonGame(game, null, dummyAction, false, onClick = {})

    }
}
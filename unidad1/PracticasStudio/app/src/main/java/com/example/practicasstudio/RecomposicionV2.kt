package com.example.practicasstudio

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

import com.example.practicasstudio.Creeper
import com.example.practicasstudio.ui.theme.*
//import kotlin.coroutines.jvm.internal.CompletedContinuation.context


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //var context = applicationContext
            var current by remember {mutableStateOf(1)}
            PracticasStudioTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background

                ) {
                    Column(
                        verticalArrangement = Arrangement.Center
                    ){

                        Text(text = "CURRENT $current", modifier = Modifier.background(Color.Green))
                        /*
                        when (current){
                            "Pig" -> Pig(onClick = {current = "skull"})
                            "skull" -> skull(onClick = {current = "Creeper"})
                            "Creeper" -> Creeper(onClick = {current = "Pig"})
                        }
                        */
                        when(current)
                        {
                            0 -> Pig(
                                context = applicationContext
                            )
                            1 -> skull(
                                context = applicationContext
                            )
                            2 -> Creeper(
                                context = applicationContext
                            )
                        }

                    }
                    Row (
                        modifier = Modifier.fillMaxHeight(), horizontalArrangement = Arrangement.SpaceBetween
                            ){
                        Button(enabled = current > 0,
                        onClick = {current -=1 }) {
                            Text(text = "Atras")
                        }
                        Button(enabled = current < 2,
                        onClick = {current +=1 }){
                            Text(text = "Siguiente")
                        }

                    }
                    //Pig()
                }
            }
        }
    }
    /*
    fun siguiente(){
        val sig = Intent(this, Creeper::class.java)
        startActivity(sig)
    }*/

}

@Composable
fun Pig(context: Context) {

    //val context = LocalContext.current
    PigBackground {
        ConstraintLayout(modifier = Modifier
            .clickable {
                MediaPlayer
                    .create(context, R.raw.pig)
                    .start()
            }
            /*.clickable(onClick = {
                Toast
                    .makeText(context, "Oink oink", Toast.LENGTH_LONG)
                    .show()
            }
            )*/
            .fillMaxSize()) {
            val (eyes, nose) = createRefs()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .constrainAs(eyes) {
                        bottom.linkTo(nose.top)
                    }, horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PigEye(position = "left")
                PigEye()
            }

            PigNose(modifier = Modifier.constrainAs(nose) {
                top.linkTo(parent.top, 100.dp)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        }
    }
}

@Composable
fun Pig(onClick : ()-> Unit) {

    val context = LocalContext.current
    //var current by remember {mutableStateOf("Pig")}
    PigBackground {
        ConstraintLayout(modifier = Modifier

            /*.clickable(onClick = {
                Toast
                    .makeText(context, "Oink oink", Toast.LENGTH_LONG)
                    .show()
            }
            )*/
            .fillMaxSize()) {
            val (eyes, nose) = createRefs()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .constrainAs(eyes) {
                        bottom.linkTo(nose.top)
                    }, horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PigEye(position = "left")
                PigEye()
            }

            PigNose(modifier = Modifier.constrainAs(nose) {
                top.linkTo(parent.top, 100.dp)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
        }
    }
}


@Composable
fun PigBackground(function: @Composable () -> Unit) {
    Box(
        /*modifier = Modifier
            .clickable {
                MediaPayer
                    .create(countext, R.raw.pig)
            }*/
            androidx.compose.ui.Modifier
            .background(basePink)
            .size(400.dp)



    ) { function() }
}

@Composable
fun PigEye(position: String = "right") {
    Row(
        modifier = Modifier
            .width(100.dp)
            .height(50.dp)
            .rotate(if (position == "left") 180.0F else 0.0F)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .size(50.dp)
            )
        }
        Column {
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .size(50.dp)
            )
        }
    }
}
@Composable
fun PigNose(modifier: Modifier) {
    Row(
        modifier = modifier
            .width(195.dp)
            .height(150.dp)
            .background(nosePink),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PigNostrils()
        PigNostrils()
    }
}

@Composable
fun PigNostrils() {
    Box(
        modifier = androidx.compose.ui.Modifier
            .background(nosePinkDark)
            .width(50.dp)
            .height(70.dp)
    )
}



@Composable
fun Creeper(context: Context) {
    val context = LocalContext.current
    CreeperBackground {
        ConstraintLayout(modifier = Modifier
            .clickable {
                MediaPlayer
                    .create(context, R.raw.creeper)
                    .start()
            }
            /*.clickable(onClick = {
                Toast
                    .makeText(context, "TsssssTsssss", Toast.LENGTH_LONG)
                    .show()
            }
            )*/
            .fillMaxSize()) {
            val (eyes, mouth, mouthFront) = createRefs()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(eyes) {
                        bottom.linkTo(mouth.top)
                    }
            ) {
                CreeperEyes()
            }

            CreeperMouth(modifier = Modifier.constrainAs(mouth){
                top.linkTo(parent.top, 200.dp)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })


            CreeperMouthFront(modifier = Modifier.constrainAs(mouthFront){
                top.linkTo(parent.top, 200.dp)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

        }
    }
}

@Composable
fun CreeperBackground(function: @Composable () -> Unit) {
    Box(
        androidx.compose.ui.Modifier
            .background(baseGreen)
            .size(400.dp)
    ) { function()

    }
}

@Composable
fun CreeperEye(position: String = "left") {
    Box(
        androidx.compose.ui.Modifier
            .background(creeperOtro)
            .size(100.dp)
    ){
        Box(
            modifier = Modifier
                .background(Color.Black)
                .size(50.dp)
                .align(if (position == "left") Alignment.BottomEnd else Alignment.BottomStart)
        )
    }
}

@Composable
fun CreeperEyes() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        CreeperEye()
        CreeperEye("right")
    }
}

@Composable
fun CreeperMouth(modifier: Modifier) {
    Row(
        modifier = modifier
            .height(200.dp)
            .width(200.dp)
            .background(Color.Transparent)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 50.dp)
                .background(creeperOtro)
                .size(50.dp)
        ) {

        }
        Column(
            modifier = Modifier
                .height(150.dp)
                .background(creeperOtro)
                .size(50.dp)
        ) {

        }
        Column(
            modifier = Modifier
                .height(150.dp)
                .background(creeperOtro)
                .size(50.dp)
        ) {

        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 50.dp)
                .background(creeperOtro)
                .size(50.dp)
        ) {

        }
    }
}

@Composable
fun CreeperMouthFront(modifier: Modifier) {
    Row(
        modifier = modifier
            .height(120.dp)
            .width(200.dp)
            .background(Color.Transparent)
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 50.dp)
                .background(Color.Black)
                .size(50.dp)
        ) {

        }
        Column(
            modifier = Modifier
                .height(150.dp)
                .background(Color.Black)
                .size(50.dp)
        ) {

        }
        Column(
            modifier = Modifier
                .height(150.dp)
                .background(Color.Black)
                .size(50.dp)
        ) {

        }
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 50.dp)
                .background(Color.Black)
                .size(50.dp)
        ) {

        }
    }
}


@Composable
fun skull(context: Context) {
    val context = LocalContext.current
    SkeletonBackground {
        ConstraintLayout(modifier = Modifier
            .clickable {
                MediaPlayer
                    .create(context, R.raw.skeleton)
                    .start()
            }
            /*.clickable(onClick = {
                Toast
                    .makeText(context, "clap clap", Toast.LENGTH_LONG)
                    .show()
            }
            )*/
            .fillMaxSize()) {
            val (eyes, nose, mouth) = createRefs()
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(eyes) {
                        bottom.linkTo(nose.top)
                    }, verticalAlignment = Alignment.Bottom
            ) {
                SkeletonEyes()
            }

            SkeltonNose(modifier = Modifier.constrainAs(nose) {
                top.linkTo(parent.top, 150.dp)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

            SkeltonMouth(modifier = Modifier.constrainAs(mouth) {
                top.linkTo(nose.bottom)
            })

        }
    }
}


@Composable
fun Skull() {
    val context = LocalContext.current
    SkeletonBackground {
        ConstraintLayout(modifier = Modifier
            /*.clickable(onClick = {
                Toast
                    .makeText(context, "clap clap", Toast.LENGTH_LONG)
                    .show()
            }
            )*/
            .fillMaxSize()) {
            val (eyes, nose, mouth) = createRefs()
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(eyes) {
                        bottom.linkTo(nose.top)
                    }, verticalAlignment = Alignment.Bottom
            ) {
                SkeletonEyes()
            }

            SkeltonNose(modifier = Modifier.constrainAs(nose) {
                top.linkTo(parent.top, 150.dp)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

            SkeltonMouth(modifier = Modifier.constrainAs(mouth) {
                top.linkTo(nose.bottom)
            })

        }
    }
}


@Composable
fun SkeltonEye() {
    Box(
        modifier = Modifier
            .background(eyesGray)
            .height(50.dp)
            .width(100.dp)
    )
}

@Composable
fun SkeletonEyes() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
        SkeltonEye()
        SkeltonEye()
    }
}

@Composable
fun SkeltonNose(modifier: Modifier) {
    Box(
        modifier = modifier
            .background(noseGray)
            .height(50.dp)
            .width(97.dp)
    )
}

@Composable
fun SkeltonMouth(modifier: Modifier) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        SkeltonEye()
        SkeltonEye()
        SkeltonEye()
    }
}


@Composable
fun SkeletonBackground(function: @Composable () -> Unit) {
    Box(
        Modifier
            .background(baseGray)
            .size(400.dp)
    ) { function() }
}





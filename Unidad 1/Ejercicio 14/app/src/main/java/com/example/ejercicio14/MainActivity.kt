package com.example.ejercicio14

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.ejercicio14.ui.theme.Ejercicio14Theme
import com.example.ejercicio14.ui.theme.basePink
import com.example.ejercicio14.ui.theme.nosePink
import com.example.ejercicio14.ui.theme.nosePinkDark

import androidx.compose.runtime.*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //  var current by remember {mutableStateOf(0)}
            var current by remember { mutableStateOf("pig") } //rememberSaveable
            Ejercicio14Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState()),
//                        verticalArrangement = Arrangement.Center,
//                        horizontalAlignment =  Alignment.CenterHorizontally
                        verticalArrangement = Arrangement.Center // 1er proyecto
                    ) {
                        Text(text = "CURRENT $current", modifier = Modifier.background(Color.Red))
                        when (current) {
                            "pig" -> HeadPig(onClick = { current = "skull" })
                            "skull" -> HeadSkull(onClick = { current = "creeper" })
                            "creeper" -> HeadCreeper(onClick = { current = "pig" })
//                        0 -> HeadPig(
//                            context =  applicationContext
//                        )
//                        1 -> HeadSkull(
//                            context = applicationContext
//                        )
//                        2 -> HeadCreeper (
//                            context = applicationContext
//                        )

                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
//                            Button(
//                                enabled = current > 0,
//                                onClick = {current -= 1}) {
//                                Text(text = "ATRAS")
//                            }
//                            Button (
//                                enabled = current < 2,
//                                onClick = {current += 1}) {
//                                Text(text = "SIGUIENTE")
//                            }
//
                            //                      }

                        }
                        //HeadPig()
                    }
                }
            }
        }
    }


    @Composable
    fun HeadPig(onClick: () -> Unit) {
        Column(
            modifier = Modifier.fillMaxSize().clickable { onClick() },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .background(basePink)
                    .width(600.dp)
                    .height(400.dp)
            ) {
                Face()
            }
        }
    }


//@Composable
//fun HeadPig(context: Context) {
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ){
//        Box(
//            modifier = Modifier
//                .background(basePink)
//                .width(400.dp)
//                .height(400.dp)
//                .clickable {
//                    MediaPlayer
//                        .create(context, R.raw.pig)
//                        .start()
//                }
//        )
//        {
//            Face()
//        }
//    }
//}


    @Composable
    fun Face() {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize(),
        ) {
            val (eyes, nose) = createRefs()
            Eyes(modifier = Modifier.constrainAs(eyes) {
                bottom.linkTo(nose.top)
            })
            Nose(modifier = Modifier
                .constrainAs(nose) {
                    top.linkTo(parent.top, 70.dp)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

        }
    }

    @Composable
    fun Eye(left: Boolean) {
        Row {
            Box(
                modifier = Modifier
                    .background(if (left) Color.Black else Color.White)
                    .width(50.dp)
                    .height(50.dp)
            )
            Box(
                modifier = Modifier
                    .background(if (left) Color.White else Color.Black)
                    .width(50.dp)
                    .height(50.dp)
            )
        }
    }

    //OJOS
    @Composable
    fun Eyes(modifier: Modifier) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {


            Eye(true)
            Eye(false)
        }

    }

    //NARIZ
    @Composable
    fun Nose(modifier: Modifier) {
        Row(
            modifier = modifier
                .width(180.dp)
                .height(120.dp)
                .background(nosePink),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            nostrils()
            nostrils()

        }
    }

    @Composable
    fun nostrils() {
        Box(
            modifier = Modifier
                .background(nosePinkDark)
                .width(40.dp)
                .height(40.dp)
        )
    }


    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        Ejercicio14Theme {
            // HeadPig()
        }
    }
}
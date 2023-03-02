package com.example.maincraface

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.maincraface.ui.theme.MaincraFaceTheme
import com.example.maincraface.ui.theme.basePink
import com.example.maincraface.ui.theme.nosePInkDark
import com.example.maincraface.ui.theme.nosePink

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var current by remember { mutableStateOf(0)}
            MaincraFaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        //Text(text = "CURRENT: $current", textAlign = TextAlign.Center  ,fontSize =50.sp, color = Color.Black, modifier = Modifier
                            //.background(Color.Red)
                            //.fillMaxWidth())
                        when(current){
                            0 -> HeadPig(context = applicationContext)
                            1 -> HeadSkull(context = applicationContext)
                            2 -> HeadCreeper(context = applicationContext)
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            enabled = current>0,
                            onClick = {current-=1}

                        ) {
                            Text(text = "Atras")
                        }
                        Button(
                            enabled = current<2,
                            onClick = {current+=1}

                        ) {
                            Text(text = "Siguiente")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
@Composable
fun Eye(left:Boolean){
    Row() {
        Box(modifier = Modifier
            .background(if (left) Color.Black else Color.White)
            .size(50.dp, 50.dp))
        Box(modifier = Modifier
            .background(if (left) Color.White else Color.Black)
            .size(50.dp, 50.dp))
    }
}
@Composable
fun Eyes(modifier: Modifier){
    Row(modifier = modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceBetween){
        Eye(left = true)
        Eye(left = false)
    }
}
@Composable
fun HeadPig(context: Context){
    Column(
        Modifier
            .fillMaxSize()
            .clickable { MediaPlayer.create(context, R.raw.pig).start() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier
                .background(basePink)
                .size(600.dp, 400.dp)){
                Face()
            }

    }
}
@Composable
fun Face(){
    ConstraintLayout(
        modifier = Modifier.fillMaxSize(),

    ) {
        val (eyes,nose) = createRefs()
        Eyes(modifier = Modifier.constrainAs(eyes){
            bottom.linkTo(nose.top)
        })
        Nose(modifier = Modifier.constrainAs(nose){
            top.linkTo(parent.top,70.dp)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
    }
}
@Composable
fun Nose(modifier: Modifier){
    Row(modifier = modifier
        .size(180.dp, 120.dp)
        .background(nosePink),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        noStrils()
        noStrils()
    }
}
@Composable
fun noStrils(){
    Box(modifier = Modifier
        .background(nosePInkDark)
        .size(40.dp, 40.dp))
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaincraFaceTheme {
        //HeadPig()
    }
}
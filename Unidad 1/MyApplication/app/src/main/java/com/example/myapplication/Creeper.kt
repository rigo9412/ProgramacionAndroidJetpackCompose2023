package com.example.myapplication

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.ui.theme.baseGreen
import com.example.myapplication.ui.theme.eyeDarker

class Creeper : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    //asknasjkdndsa aiudaa
                    //HeadCreeper()
                }
            }
        }
    }
}

@Composable
fun HeadCreeper(/*onClick: () -> Unit*/context: Context?){
    Column(
        modifier = Modifier.fillMaxSize()/*.clickable { onClick() }*/,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Box(modifier = Modifier
            .background(baseGreen)
            .width(400.dp)
            .height(400.dp)
            .clickable {
                MediaPlayer
                    .create(context, R.raw.creeper)
                    .start()
            }

        ){
            ConstraintLayout (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(50.dp))
            {
                val face = createRef()
                FaceCreeper(modifier = Modifier.constrainAs(face) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })

            }
        }

    }
}

@Composable
fun FaceCreeper(modifier: Modifier){
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally){
        EyesCreeper()
        MouthUpCreeper()
    }
}

@Composable
fun EyesCreeper(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        EyeCreeper()
        EyeCreeper2()
    }
}

@Composable
fun MouthUpCreeper(){
    Row() {
        Box(modifier = Modifier
            .background(eyeDarker)
            .width(50.dp)
            .height(40.dp))
        Box(modifier = Modifier
            .background(eyeDarker)
            .width(50.dp)
            .height(40.dp))
    }
    Row() {
        Box(modifier = Modifier
            .background(eyeDarker)
            .width(50.dp)
            .height(40.dp))
        Box(modifier = Modifier
            .background(Color.Black)
            .width(50.dp)
            .height(40.dp))
        Box(modifier = Modifier
            .background(Color.Black)
            .width(50.dp)
            .height(40.dp))
        Box(modifier = Modifier
            .background(eyeDarker)
            .width(50.dp)
            .height(40.dp))
    }
    Row() {
        Box(modifier = Modifier
            .background(Color.Black)
            .width(50.dp)
            .height(50.dp))
        Box(modifier = Modifier
            .background(Color.Black)
            .width(50.dp)
            .height(50.dp))
        Box(modifier = Modifier
            .background(Color.Black)
            .width(50.dp)
            .height(50.dp))
        Box(modifier = Modifier
            .background(Color.Black)
            .width(50.dp)
            .height(50.dp))
    }
    Row() {
        Box(modifier = Modifier
            .background(Color.Black)
            .width(50.dp)
            .height(50.dp))
        Box(modifier = Modifier
            .background(baseGreen)
            .width(50.dp)
            .height(50.dp))
        Box(modifier = Modifier
            .background(baseGreen)
            .width(50.dp)
            .height(50.dp))
        Box(modifier = Modifier
            .background(Color.Black)
            .width(50.dp)
            .height(50.dp))
    }
}


@Composable
fun EyeCreeper(){
    Box(modifier = Modifier
        .background(eyeDarker)
        .width(100.dp)
        .height(80.dp),
        contentAlignment = Alignment.BottomEnd){
        Box(
            modifier = Modifier
                .background(Color.Black)
                .width(65.dp)
                .height(55.dp)
        )
    }
}
@Composable
fun EyeCreeper2(){
    Box(modifier = Modifier
        .background(eyeDarker)
        .width(100.dp)
        .height(80.dp),
        contentAlignment = Alignment.BottomStart){
        Box(
            modifier = Modifier
                .background(Color.Black)
                .width(65.dp)
                .height(55.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewCreeper() {
    MyApplicationTheme {
        HeadCreeper(context = null)
    }
}
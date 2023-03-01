package com.example.pig

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Preview(showBackground = true)
@Composable
fun DefaultPreviewSkull() {
}

@Composable
fun HeadSkull(context: Context){

    Column(
        modifier = Modifier.fillMaxSize()
            .clickable {
                MediaPlayer
                    .create(context, R.raw.skeleton2)
                    .start()
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Box(modifier = Modifier
            .background(Color.LightGray)
            .width(400.dp)
            .height(350.dp)
        ){
            ConstraintLayout(modifier = Modifier
                .fillMaxSize()
                .padding(45.dp)) {
                val face = createRef()
                FaceSkull(modifier = Modifier.constrainAs(face){
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            }
        }
    }
}

@Composable
fun Mouth() {
    Box(modifier = Modifier
        .background(Color.Black)
        .fillMaxWidth()
        .height(50.dp)
    )
}

@Composable
fun NoseSkull() {
    Box(modifier = Modifier
        .background(Color.Gray)
        .width(100.dp)
        .height(50.dp)
    )
}

@Composable
fun EyesSkull() {
    Row( modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
        ) {
        EyeSkull()
        EyeSkull()
    }
}

@Composable
fun FaceSkull(modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EyesSkull()
        NoseSkull()
        Mouth()
    }
}

@Composable
fun EyeSkull() {
    Box(modifier = Modifier
        .background(Color.Black)
        .width(100.dp)
        .height(50.dp))
}

package com.example.minecraft.ui.theme

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.ui.graphics.Color
/*
@Composable
fun HeadSkull(onClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().clickable { onClick() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(Color.LightGray)
                .size(400.dp)
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(45.dp)
            ) {
                val face = createRef()
                FaceSkull(modifier = Modifier.constrainAs(face){
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            }
        }
    }
}*/
@Composable
fun HeadSkull(Context : Context?) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(Color.LightGray)
                .size(400.dp)
                .clickable { MediaPlayer
                    .create(Context, com.example.minecraft.R.raw.skeleton)
                    .start()
                }
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(45.dp)
            ) {
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
fun FaceSkull(modifier : Modifier){
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        EyesSkull()
        NoseSkull()
        MouthSkull()
    }
}
@Composable
fun EyesSkull(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        EyeSkull()
        EyeSkull()

    }
}
@Composable
fun EyeSkull(){
    Row() {
        Box(
            modifier = Modifier
                .height(50.dp)
                .width(100.dp)
                .background(Color.Black)
        )

    }
}
@Composable
fun NoseSkull(){
    Row() {
        Box(
            modifier = Modifier
                .background(Color.Gray)
                .height(50.dp)
                .width(100.dp)
        )
    }
}
@Composable
fun MouthSkull(){
    Row() {
        Box(
            modifier = Modifier
                .background(Color.Black)
                .height(50.dp)
                .fillMaxWidth()
        )
    }
}
package com.example.minecraft.ui.theme

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

val basePink = Color(0xFFEF9A9A)
val nosePink = Color(0XFFFFCDD2)
val nosePinkDark = Color(0XFF884B4A)
/*
@Composable
fun HeadPig(onClick: () -> Unit){
    Column(modifier = Modifier.fillMaxSize().clickable { onClick() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier
            .background(basePink)
            .size(400.dp)
        ){
            Face()
        }
    }

}*/
@Composable
fun HeadPig(Context : Context?){
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier
            .background(basePink)
            .size(400.dp)
            .clickable { MediaPlayer
                .create(Context, com.example.minecraft.R.raw.pig)
                .start()
            }
        ){
            Face()
        }
    }
}
@Composable
fun Face(){
    ConstraintLayout(modifier = Modifier.fillMaxSize(),
    ) {
        val (eyes, nose) = createRefs()
        Eyes(modifier = Modifier.constrainAs(eyes) {
            bottom.linkTo(nose.top)
        })
        Nose(modifier = Modifier.constrainAs(nose) {
            top.linkTo(parent.top,70.dp)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
    }
}

@Composable
fun Eye(left:Boolean){
    Row{
        Box(modifier= Modifier
            .background(if (left) Color.Black else Color.White)
            .width(50.dp)
            .height(50.dp)
        ){

        }
        Box(
            modifier = Modifier
                .background(if (left) Color.White else Color.Black)
                .width(50.dp)
                .height(50.dp)
        )
    }
}

@Composable
fun Nose(modifier: Modifier){
    Row(modifier = modifier
        .width(180.dp)
        .height(120.dp)
        .background(nosePink),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Nostrils()
        Nostrils()
    }
}

@Composable
fun Eyes(modifier: Modifier){
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Eye(true)
        Eye(false)
    }
}

@Composable
fun Nostrils(){
    Box(modifier = Modifier
        .background(nosePinkDark)
        .width(40.dp)
        .height(40.dp))
}


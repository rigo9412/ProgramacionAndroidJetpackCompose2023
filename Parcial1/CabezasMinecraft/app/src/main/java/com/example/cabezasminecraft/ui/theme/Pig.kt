package com.example.cabezasminecraft.ui.theme

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
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.cabezasminecraft.R

import com.example.cabezasminecraft.ui.theme.CabezasMinecraftTheme


class Pig : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CabezasMinecraftTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                }
            }
        }
    }
}

@Composable
fun Eye(left: Boolean)
{
    Row(){
        Box(modifier = Modifier
            .background(if (left) Color.Black else Color.White)
            .width(50.dp)
            .height(50.dp))
        Box(modifier = Modifier
            .background(if (left) Color.White else Color.Black)
            .width(50.dp)
            .height(50.dp))
    }
}

@Composable
fun Eyes(modifier: Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Eye( left=true)
        Eye( left=false)
    }
}

@Composable
fun Nostrils(){
    Box(
        modifier = Modifier
            .background(nosePinkDark)
            .width(40.dp)
            .height(40.dp)
    )
}

@Composable
fun Nose(modifier: Modifier){
    Row(
        modifier = modifier
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
fun Face(){
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (eyes,nose) = createRefs()
        Eyes(modifier = Modifier.constrainAs(eyes){
            bottom.linkTo(nose.top)
        })
        Nose(modifier = Modifier
            .constrainAs(nose) {
                top.linkTo(parent.top,70.dp)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
    }
}

@Composable
fun HeadPig(/*onClick: () -> Unit,*/ context: Context?){
    Column(
        modifier = Modifier.fillMaxSize()/*.clickable { onClick() }*/,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .background(basePink)
                .width(600.dp)
                .height(400.dp)
                .clickable {
                    MediaPlayer
                        .create(context, R.raw.pig)
                        .start()
                }
        ) {
            Face()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreviewPig() {
    CabezasMinecraftTheme {
        Face()
    }
}
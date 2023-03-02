package com.example.desarrolloandroid

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.R
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
import com.example.desarrolloandroid.ui.theme.DesarrolloAndroidTheme
import com.example.desarrolloandroid.ui.theme.baseGray




@Composable
fun HeadSkull(context: Context?){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Box(
            modifier = Modifier
                .background(baseGray)
                .width(600.dp)
                .height(400.dp)
                .clickable {
                    MediaPlayer
                        .create(context, R.raw.skeleton)
                        .start()
                }
        )
        {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(45.dp),
            ){
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


//@Composable
//fun HeadSkull(onClick: () -> Unit /*context: Context?*/){
//    Column(
//        modifier = Modifier.fillMaxSize().clickable {onClick()},
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//    ){
//        Box(
//            modifier = Modifier
//                .background(baseGray)
//                .width(600.dp)
//                .height(400.dp)
//
//        )
//        {
//            ConstraintLayout(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(45.dp),
//            ){
//                val face = createRef()
//                FaceSkull(modifier = Modifier.constrainAs(face){
//                    bottom.linkTo(parent.bottom)
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                })
//            }
//        }
//    }
//}

@Composable
fun FaceSkull(modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment =  Alignment.CenterHorizontally
    ){
        EyesSkull()
        NoseSkull()
        Mouth()
    }
}

@Composable
fun EyesSkull()
{
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        EyeSkull()
        EyeSkull()
    }
}


@Composable
fun Eye(){
    Box(
        modifier = Modifier
            .background(Color.Black)
            .width(1000.dp)
            .height(50.dp)
    )
}

@Composable
fun EyeSkull(){
    Box(
        modifier = Modifier
            .background(Color.Black)
            .width(100.dp)
            .height(50.dp)
    )
}

@Composable
fun Mouth(){
    Box(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxWidth()
            .height(50.dp)
    )
}

@Composable
fun NoseSkull()
{
    Box(
        modifier = Modifier
            .background(Color.Gray)
            .width(100.dp)
            .height(50.dp)
    )
}


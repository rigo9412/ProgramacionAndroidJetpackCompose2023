package com.example.desarrolloandroid

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
import com.example.desarrolloandroid.ui.theme.DesarrolloAndroidTheme
import com.example.desarrolloandroid.ui.theme.baseGreen
import com.example.desarrolloandroid.ui.theme.baseDarkGray



@Composable
fun HeadCreeper(context: Context?){
    Column(
        modifier = Modifier.fillMaxSize(),
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


//@Composable
//fun HeadCreeper(onClick: () -> Unit){
//    Column(
//        modifier = Modifier.fillMaxSize().clickable { onClick() },
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally){
//        Box(modifier = Modifier
//            .background(baseGreen)
//            .width(400.dp)
//            .height(400.dp)
//        ){
//            ConstraintLayout (
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(50.dp))
//            {
//                val face = createRef()
//                FaceCreeper(modifier = Modifier.constrainAs(face) {
//                    bottom.linkTo(parent.bottom)
//                    start.linkTo(parent.start)
//                    end.linkTo(parent.end)
//                })
//
//            }
//        }
//    }
//}


@Composable
fun FaceCreeper(modifier: Modifier){
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally){
        EyesCreeper()
        MouthCreeper()

    }
}

@Composable
fun EyesCreeper(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        EyeCreeper(true)
        EyeCreeper(false)
    }
}



@Composable
fun MouthCreeper(){
    Row() {
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
            .background(Color.Black)
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
fun EyeCreeper(opcion:Boolean){
    Box(modifier = Modifier
        .background(Color.Black)
        .width(100.dp)
        .height(90.dp),
        contentAlignment = if (opcion) Alignment.BottomEnd else Alignment.BottomStart){
        Box(
            modifier = Modifier
                .background(baseDarkGray)
                .width(50.dp)
                .height(50.dp)
        )
    }

}


//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview3() {
//    DesarrolloAndroidTheme {
//       HeadCreeper()
//    }
//}
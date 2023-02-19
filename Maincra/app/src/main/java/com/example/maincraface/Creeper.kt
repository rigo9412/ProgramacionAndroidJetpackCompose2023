package com.example.maincraface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showBackground = true)

@Composable
fun DefaultPreviewCreeper() {
    HeadCreeper()
}

@Composable
fun HeadCreeper()
{
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(modifier = Modifier
            .background(Color(7, 205, 38))
            .width(400.dp)
            .height(400.dp)
        ){
            FaceCreeper()
        }

    }

}

@Composable
fun FaceCreeper(){
    Column (horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(0.dp,50.dp)
    ){
        EyesCreeper()
        MouthCreeper()
    }
}

@Composable
fun EyesCreeper(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(7, 205, 38)),
    ) {
        Spacer(modifier = Modifier.width(50.dp))
        EyeCreeper(left = true)
        Spacer(modifier = Modifier.width(100.dp))
        EyeCreeper(left = false)
    }
}

@Composable
fun MouthCreeper(){
    Row() {
        Box(modifier = Modifier
            .background(Color(color = 0xFF3A5119))
            .width(100.dp)
            .height(50.dp))
    }
    Row() {
        Box(modifier = Modifier
            .background(Color(color = 0xFF3A5119))
            .width(50.dp)
            .height(50.dp))
        Box(modifier = Modifier
            .background(Color.Black)
            .width(100.dp)
            .height(50.dp))
        Box(modifier = Modifier
            .background(Color(color = 0xFF3A5119))
            .width(50.dp)
            .height(50.dp))
    }
    Row() {
        Box(modifier = Modifier
            .background(Color.Black)
            .width(200.dp)
            .height(50.dp))
    }
    Row() {
        Box(modifier = Modifier
            .background(Color.Black)
            .width(50.dp)
            .height(50.dp))
        Box(modifier = Modifier
            .background(Color(color = 0xFF3A5119))
            .width(100.dp)
            .height(50.dp))
        Box(modifier = Modifier
            .background(Color.Black)
            .width(50.dp)
            .height(50.dp))
    }
}

@Composable
fun EyeCreeper(left: Boolean){
    Row() {
        Column() {
            Box(modifier = Modifier
                .background(Color.Black)
                .width(50.dp)
                .height(50.dp))
            Box(modifier = Modifier
                .background(if (left) Color.Black else Color(color = 0xFF3A5119))
                .width(50.dp)
                .height(50.dp))
        }
        Column() {
            Box(modifier = Modifier
                .background(Color.Black)
                .width(50.dp)
                .height(50.dp))
            Box(modifier = Modifier
                .background(if (left) Color(color = 0xFF3A5119) else Color.Black)
                .width(50.dp)
                .height(50.dp))
        }
    }
}

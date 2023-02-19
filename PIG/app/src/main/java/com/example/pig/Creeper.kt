package com.example.pig

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.pig.ui.theme.PIGTheme
import com.example.pig.ui.theme.darkGreenCreeper
import com.example.pig.ui.theme.transparent
import com.google.android.gms.maps.model.Cap

@Preview(showBackground = true)
@Composable
fun DefaultPreviewCreeper() {
    PIGTheme {
        HeadCreeper()
    }
}

@Composable
fun HeadCreeper() {
    Column(modifier = Modifier
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,) {
        Box(modifier = Modifier
            .background(Color.Green)
            .width(400.dp)
            .height(400.dp)
        ){
            FaceCreeper()
        }

    }
}

@Composable
fun FaceCreeper() {
    Column (horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(0.dp,50.dp)
        ){
        EyesCreeper()
        MouthCreeper()
    }
}

@Composable
fun MouthCreeper() {
    Row() {
        Box(modifier = Modifier
            .background(darkGreenCreeper)
            .width(50.dp)
            .height(50.dp))
        Box(modifier = Modifier
            .background(darkGreenCreeper)
            .width(50.dp)
            .height(50.dp))
    }
    Row() {
        Box(modifier = Modifier
            .background(darkGreenCreeper)
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
            .background(darkGreenCreeper)
            .width(50.dp)
            .height(50.dp))
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
            .background(darkGreenCreeper)
            .width(50.dp)
            .height(50.dp))
        Box(modifier = Modifier
            .background(transparent)
            .width(50.dp)
            .height(50.dp))
        Box(modifier = Modifier
            .background(transparent)
            .width(50.dp)
            .height(50.dp))
        Box(modifier = Modifier
            .background(darkGreenCreeper)
            .width(50.dp)
            .height(50.dp))
    }
}

@Composable
fun EyesCreeper() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(transparent),
    ) {
        Spacer(modifier = Modifier.width(47.dp))
        EyeCreeper(left = true)
        Spacer(modifier = Modifier.width(97.dp))
        EyeCreeper(left = false)
    }
}

@Composable
fun EyeCreeper(left: Boolean) {
    Row() {
        Column() {
            Box(modifier = Modifier
                .background(darkGreenCreeper)
                .width(50.dp)
                .height(50.dp))
            Box(modifier = Modifier
                .background(if (left) darkGreenCreeper else Color.Black)
                .width(50.dp)
                .height(50.dp))
        }
        Column() {
            Box(modifier = Modifier
                .background(darkGreenCreeper)
                .width(50.dp)
                .height(50.dp))
            Box(modifier = Modifier
                .background(if (left) Color.Black else darkGreenCreeper)
                .width(50.dp)
                .height(50.dp))
        }
    }
}


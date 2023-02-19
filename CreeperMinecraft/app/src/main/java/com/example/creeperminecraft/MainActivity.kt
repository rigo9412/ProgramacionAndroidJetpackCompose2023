package com.example.creeperminecraft

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.creeperminecraft.ui.theme.CreeperMinecraftTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

val creeperBase = Color(0xFF43B619)
val black = Color.Black
val creeperMouth = Color(0xFF26700B)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreeperMinecraftTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Creeper()
                }
            }
        }
    }
}
@Composable
fun Creeper() {
    Column(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)) {
        Box(modifier = Modifier
            .background(creeperBase)
            .size(400.dp)) {
            CreeperFace()
        }
    }
}

@Composable
fun CreeperFace() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val(eyes, mouth, effect) = createRefs()

        CreeperEyes(modifier = Modifier.constrainAs(eyes) {
            top.linkTo(parent.top, 100.dp)
            start.linkTo(parent.start, 50.dp)
        })

        CreeperM3(modifier = Modifier.constrainAs(effect) {
            start.linkTo(eyes.start, 50.dp)
            top.linkTo(eyes.top, 50.dp)
        })

        CreeperMouth(modifier = Modifier.constrainAs(mouth) {
            top.linkTo(parent.top, 200.dp)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
    }
}

@Composable
fun CreeperEyes(modifier: Modifier) {
    Row (
        modifier = modifier.width(300.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CreeperEye()
        CreeperEye()
    }
}

@Composable
fun CreeperEye() {
    Box(
        modifier = Modifier
            .background(black)
            .width(100.dp)
            .height(100.dp)
    )
}

@Composable
fun CreeperMouth(modifier: Modifier) {
    ConstraintLayout (
        modifier =
        modifier.size(200.dp)
    ){
        val(p1, p2, p3) = createRefs()

        CreeperM1(modifier = Modifier.constrainAs(p1) {
            bottom.linkTo(p2.top)
            start.linkTo(p2.start, 50.dp)
        })

        CreeperM2(modifier = Modifier.constrainAs(p2) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })

        CreeperM3(modifier = Modifier.constrainAs(p3) {
            top.linkTo(p2.bottom)
            start.linkTo(p2.start)
        })
    }
}

@Composable
fun CreeperM1(modifier: Modifier) {
    Box(
        modifier = modifier
            .background(creeperMouth)
            .width(100.dp)
            .height(50.dp)
    ){}
}

@Composable
fun CreeperM2(modifier: Modifier) {
    Box(
        modifier = modifier
            .background(black)
            .width(200.dp)
            .height(100.dp)
    ){
        CreeperM3(modifier)
    }
}

@Composable
fun CreeperM3(modifier: Modifier) {
    Row (
        modifier = modifier.width(200.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CreeperPoint()
        CreeperPoint()
    }
}

@Composable
fun CreeperPoint() {
    Box(
        modifier = Modifier
            .background(creeperMouth)
            .size(50.dp)
    ){}
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CreeperMinecraftTheme {
        Creeper()
    }
}
package com.example.cerditominecraft

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.cerditominecraft.ui.theme.CerditoMinecraftTheme

import androidx.constraintlayout.compose.ConstraintLayout

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CerditoMinecraftTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HeadSkull()

                }
            }
        }
    }
}

@Composable
fun HeadPig() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(basePink)
                .width(600.dp)
                .height(400.dp)
        ) {
            Face()
        }
    }

}

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0XFF03DAC5)

val basePink = Color(0xFFEF9A9A)
val nosePink = Color(0XFFFFCDD2)
val nosePinkDark = Color(0XFF884B4A)

val creeperBase = Color(0xFF43B619)
val black = Color.Black
val creeperMouth = Color(0xFF26700B)
@Composable
fun Face() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize(),
    ) {
        val (eyes, nose) = createRefs()
        Eyes(modifier = Modifier.constrainAs(eyes) {
            bottom.linkTo(nose.top)
        })
        Nose(modifier = Modifier.constrainAs(nose) {
            top.linkTo(parent.top, 70.dp)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
    }
}

@Composable
fun Eye(left: Boolean) {
    Row() {
        Box(
            modifier = Modifier
                .background(if (left) Color.Black else Color.White)
                .width(50.dp)
                .height(50.dp)
        ) {

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
fun Nose(modifier: Modifier) {
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
fun Eyes(modifier: Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Eye(true)
        Eye(false)
    }
}

@Composable
fun Nostrils() {
    Box(
        modifier = Modifier
            .background(nosePinkDark)
            .width(40.dp)
            .height(40.dp)
    )
}


@Composable
fun EyeSkeleton() {
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
fun EyesSkeleton() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        EyeSkeleton()
        EyeSkeleton()

    }
}

@Composable
fun HeadSkull() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(Color.LightGray)
                .width(600.dp)
                .height(400.dp)
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(45.dp)
            ) {
                val face = createRef()
                FaceSkeleton(modifier = Modifier.constrainAs(face){
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            }
        }
    }
}


@Composable
fun FaceSkeleton(modifier: Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        EyesSkeleton()
        NoseSkeleton()
        MouthSkeleton()
    }

}


@Composable
fun NoseSkeleton() {
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
fun MouthSkeleton() {
    Row() {
        Box(
            modifier = Modifier
                .background(Color.Black)
                .height(50.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun HeadCreeper() {
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
    CerditoMinecraftTheme {
        Row(modifier = Modifier.fillMaxSize()) {
           HeadCreeper()
        }
    }
}
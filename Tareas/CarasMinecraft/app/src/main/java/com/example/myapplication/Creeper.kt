package com.example.myapplication

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
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.myapplication.ui.theme.MyApplicationTheme

class Creeper : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HeadCreeper(null)
                }
            }
        }
    }
}

val creeperGreen = Color(0xFF34E434)
val mutedGreen= Color(0xFF004400)
val creeperBlack = Color(0xFF000000)

@Composable
fun HeadCreeper(context: Context?){
    Box(modifier = Modifier
        .background(creeperGreen)
        .width(600.dp)
        .height(400.dp)
        .clickable {
            MediaPlayer
                .create(context, R.raw.creeper)
                .start()
        }){
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(47.dp)
        ) {
            FaceC()
        }
    }

//    Column(
//        modifier = Modifier.fillMaxSize().clickable { onClick() },
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally,
//
//        ) {
//        Box(
//            modifier = Modifier
//                .background(creeperGreen)
//                .width(600.dp)
//                .height(400.dp)
//        ) {
//            ConstraintLayout(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(47.dp)
//            ) {
//                FaceC()
//            }
//        }
//    }
}


@Composable
fun FaceC(){
    ConstraintLayout(modifier = Modifier.fillMaxSize(),){
        val (eyes,mouth) = createRefs()
        EyesC(modifier = Modifier.constrainAs(eyes){
            bottom.linkTo(mouth.top)
        })
        MouthC(modifier = Modifier.constrainAs(mouth){
            top.linkTo(parent.top,195.dp)
            bottom.linkTo((parent.bottom))
        })
    }
}

@Composable
fun EyesC(modifier: Modifier){
    Row(
        modifier = modifier
            .padding(top = 50.dp)
            .width(300.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        EyeC(left = true)
        EyeC(left = false)
    }
}

@Composable
fun MouthC(modifier: Modifier){
    ConstraintLayout(modifier = Modifier.fillMaxWidth(),){
        Column(modifier = modifier){
            Row(
                modifier = modifier
                    .width(300.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                MouthPixel(dark = true)
                MouthPixel(dark = true)
            }
            Row(
                modifier = modifier
                    .width(300.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                MouthPixel(dark = true)
                MouthPixel(dark = false)
                MouthPixel(dark = false)
                MouthPixel(dark = true)
            }
            Row(
                modifier = modifier
                    .width(300.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                MouthPixel(dark = false)
                MouthPixel(dark = false)
                MouthPixel(dark = false)
                MouthPixel(dark = false)
            }
            Row(
                modifier = modifier
                    .width(300.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                MouthPixel(dark = true)
                Box(modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                )
                Box(modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                )
                MouthPixel(dark = true)
            }
        }
    }
}

@Composable
fun MouthPixel(dark: Boolean){
    Box(
        modifier = Modifier
            .background(if (dark) mutedGreen else creeperBlack)
            .width(50.dp)
            .height(50.dp)
    )
}

@Composable
fun EyeC(left: Boolean){
    Row{
        Column{
            Box(modifier = Modifier
                .background(mutedGreen)
                .width(50.dp)
                .height(50.dp)
            )
            Box(modifier = Modifier
                .background(if (!left) creeperBlack else mutedGreen)
                .width(50.dp)
                .height(50.dp)
            )
        }
        Column{
            Box(modifier = Modifier
                .background(mutedGreen)
                .width(50.dp)
                .height(50.dp)
            )
            Box(modifier = Modifier
                .background(if (left) creeperBlack else mutedGreen)
                .width(50.dp)
                .height(50.dp)
            )
        }
    }
}


@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    MyApplicationTheme {
        HeadCreeper(null)
    }
}
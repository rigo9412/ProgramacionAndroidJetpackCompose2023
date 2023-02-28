package com.example.minecraftfaces

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.minecraftfaces.ui.theme.MinecraftFacesTheme

class Creeper : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MinecraftFacesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //CreeperHead()
                }
            }
        }
    }
}

val SkinCreeper = Color(0xFF4ABE3A)
val mouthCreeper1 = Color(0xFF0E160D)

@Composable
fun CreeperHead(context: Context){
    Column(
        modifier = Modifier.size(600.dp,400.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .background(SkinCreeper)
                .width(600.dp)
                .height(400.dp)
                .clickable {
                    MediaPlayer
                        .create(context, R.raw.creeper)
                        .start()
                }
        )
        {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(45.dp)
            ){
                CreeperFace()
            }
        }
    }
}

@Composable
fun CreeperFace(){
    ConstraintLayout(modifier = Modifier.fillMaxSize(),){
        val (eyes,nose,mouth1,mouth2,mouth3) = createRefs()
        CreeperEyes(modifier = Modifier.constrainAs(eyes){
            bottom.linkTo(nose.top)
        })
        CreeperNose(modifier = Modifier.constrainAs(nose){
            top.linkTo(parent.top,48.dp)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
        Mouth1(modifier = Modifier.constrainAs(mouth1){
            top.linkTo(nose.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
        Mouth2(modifier = Modifier.constrainAs(mouth2){
            top.linkTo(mouth1.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
        Mouth3(modifier = Modifier.constrainAs(mouth3){
            top.linkTo(mouth2.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
    }
}

@Composable
fun CreeperEyes(modifier: Modifier){
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CreeperEye(left = true)
        CreeperEye(left = false)
    }
}

@Composable
fun CreeperEye(left: Boolean){
    Row{
        Box(modifier = Modifier //Lado Der
            .background(mouthCreeper1)
            .width(50.dp)
            .height(100.dp)
        )
        Box(modifier = Modifier //Lado Izq
            .background(mouthCreeper1)
            .width(50.dp)
            .height(100.dp)

        )
    }
}

@Composable
fun CreeperNose(modifier: Modifier){
    Row(
        modifier = modifier
            .width(100.dp)
            .height(50.dp)
            .background(mouthCreeper1),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){

    }
}

@Composable
fun Mouth1(modifier: Modifier){
    Row(
        modifier = modifier
            .width(200.dp)
            .height(50.dp)
            .background(Color.Black),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(modifier = Modifier
            .background(mouthCreeper1)
            .width(50.dp)
            .height(50.dp)
        )
        Box(modifier = Modifier
            .background(mouthCreeper1)
            .width(50.dp)
            .height(50.dp)
        )
    }
}

@Composable
fun Mouth2(modifier: Modifier){
    Row(
        modifier = modifier
            .width(200.dp)
            .height(50.dp)
            .background(Color.Black),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
    }
}

@Composable
fun Mouth3(modifier: Modifier){
    Row(
        modifier = modifier
            .width(200.dp)
            .height(50.dp)
            .background(SkinCreeper),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(modifier = Modifier
            .background(mouthCreeper1)
            .width(50.dp)
            .height(50.dp)
        )
        Box(modifier = Modifier
            .background(mouthCreeper1)
            .width(50.dp)
            .height(50.dp)
        )
    }
}
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview4() {
//    MinecraftFacesTheme {
//        CreeperHead()
//    }
//}
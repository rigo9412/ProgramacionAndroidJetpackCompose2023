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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.minecraftfaces.ui.theme.MinecraftFacesTheme

class Pig : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MinecraftFacesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                  //  HeadPig()
                }
            }
        }
    }
}


val basePink = Color(0xFFEF9A9A)
val nosePink = Color(0XFFFFCDD2)
val nosePinkDark = Color(0XFF884B4A)


@Composable
fun HeadPig(context: Context) {
    Column(
        modifier = Modifier.size(600.dp,400.dp),
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
        )
        {
             Face()
        }
    }
}

@Composable
fun Face(){
    ConstraintLayout(modifier = Modifier.fillMaxSize(),){
        val (eyes,nose) = createRefs()
        Eyes(modifier = Modifier.constrainAs(eyes){
            bottom.linkTo(nose.top)
        })
        Nose(modifier = Modifier.constrainAs(nose){
            top.linkTo(parent.top,70.dp)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
    }
}

@Composable
fun Eyes(modifier: Modifier){
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Eye(left = true)
        Eye(left = false)
    }
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
    ){
        Nostrils()
        Nostrils()
    }
}

@Composable
fun Nostrils(){
    Box(modifier = Modifier
        .background(nosePinkDark)
        .width(40.dp)
        .height(40.dp)
    )
}

@Composable
fun Eye(left: Boolean){
    Row{
        Box(modifier = Modifier
            .background(if (left) Color.Black else Color.White)
            .width(50.dp)
            .height(50.dp)
        )
        Box(modifier = Modifier
            .background(if (left) Color.White else Color.Black)
            .width(50.dp)
            .height(50.dp)
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview2() {
//    MinecraftFacesTheme {
//        HeadPig()
//    }
//}
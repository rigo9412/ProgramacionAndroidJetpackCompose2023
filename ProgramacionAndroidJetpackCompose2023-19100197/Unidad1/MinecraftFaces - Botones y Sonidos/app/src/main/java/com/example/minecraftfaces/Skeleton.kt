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

class Skeleton : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MinecraftFacesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //SkeletonHead
                }
            }
        }
    }
}

val baseBone = Color(0xFFDBCACA)
val noseBone = Color(0xFF7A7777)

@Composable
fun SkeletonHead(context: Context){
    Column(
        modifier = Modifier.size(600.dp,400.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .background(baseBone)
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
                    .padding(45.dp)
            ){
                SkeletonFace()
            }
        }
    }
}


@Composable
fun SkeletonFace(){
    ConstraintLayout(modifier = Modifier.fillMaxSize(),){
        val (eyes,nose,mouth) = createRefs()
        SkeletonEyes(modifier = Modifier.constrainAs(eyes){
            bottom.linkTo(nose.top)
        })
        SkeletonNose(modifier = Modifier.constrainAs(nose){
            top.linkTo(parent.top,80.dp)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
        Mouth(modifier = Modifier.constrainAs(mouth){
            top.linkTo(nose.bottom)
        })
    }
}

@Composable
fun SkeletonEyes(modifier: Modifier){
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SkeletonEye(left = true)
        SkeletonEye(left = false)
    }
}

@Composable
fun SkeletonNose(modifier: Modifier){
    Row(
        modifier = modifier
            .width(100.dp)
            .height(50.dp)
            .background(noseBone),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        //Aqui estaba el nostrils
    }
}

@Composable
fun Mouth(modifier: Modifier){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.Black),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){

    }
}

@Composable
fun SkeletonEye(left: Boolean){
    Row{
        Box(modifier = Modifier
            .background(Color.Black)
            .width(50.dp)
            .height(50.dp)
        )
        Box(modifier = Modifier
            .background(Color.Black)
            .width(50.dp)
            .height(50.dp)
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview3() {
//    MinecraftFacesTheme {
//        SkeletonHead()
//    }
//}
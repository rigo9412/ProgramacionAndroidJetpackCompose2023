package com.example.skullminecraft

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.skullminecraft.ui.theme.SkullMinecraftTheme
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkullMinecraftTheme {
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
                FaceSkull(modifier = Modifier.constrainAs(face){
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                })
            }
        }
    }
}

@Composable
fun FaceSkull(modifier : Modifier){
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        EyesSkull()
        NoseSkull()
        MouthSkull()
    }
}
@Composable
fun EyesSkull(){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        EyeSkull()
        EyeSkull()

    }
}
@Composable
fun EyeSkull(){
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
fun NoseSkull(){
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
fun MouthSkull(){
    Row() {
        Box(
            modifier = Modifier
                .background(Color.Black)
                .height(50.dp)
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SkullMinecraftTheme {
        HeadSkull()
    }
}
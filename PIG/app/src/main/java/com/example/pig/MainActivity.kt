package com.example.pig

import android.media.FaceDetector.Face
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.pig.ui.theme.PIGTheme
import com.example.pig.ui.theme.basePink
import com.example.pig.ui.theme.nosePink
import com.example.pig.ui.theme.nosePinkDark

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PIGTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PIGTheme {
        HeadPig()
    }
}

@Composable
fun Nostrils() {
    Box(modifier = Modifier
        .background(nosePinkDark)
        .width(40.dp)
        .height(40.dp)
    )
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
    ) {
        Nostrils()
        Nostrils()
    }
}

@Composable
fun Eye(left:  Boolean){
    Row {
        Box(modifier = Modifier
            .background(if (left) Color.Black else Color.White)
            .size(50.dp, 50.dp))
        Box(modifier = Modifier
            .background(if (left) Color.White else Color.Black)
            .size(50.dp, 50.dp))
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
fun Face() {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize(),
    ) {
        val (eyes,nose) = createRefs()
        Eyes(modifier = Modifier.constrainAs(eyes){
            bottom.linkTo(nose.top)
        })
        Nose(modifier = Modifier
            .constrainAs(nose){
                top.linkTo(parent.top,70.dp)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        )
    }
}

@Composable
fun HeadPig(){
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Box(modifier = Modifier
            .background(basePink)
            .width(600.dp)
            .height(400.dp)
        ){
            Face()
        }
    }
}

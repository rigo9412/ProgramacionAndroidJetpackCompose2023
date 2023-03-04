package com.example.minecraft19100155

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.minecraft19100155.ui.theme.Minecraft19100155Theme
import com.example.minecraft19100155.ui.theme.basePink
import com.example.minecraft19100155.ui.theme.nosePink
import com.example.minecraft19100155.ui.theme.nosePinkDark

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var current by rememberSaveable() { mutableStateOf(value = 0) }
            Minecraft19100155Theme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(modifier = Modifier.verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.Center){
                       // Text( text = "CURRENT $current", modifier = Modifier.background(Color.Red))
                        when(current){
                            0 -> HeadPig (context = applicationContext)
                            1 -> HeadSkull(context = applicationContext)
                            2 -> Creeper(context = applicationContext)
//                            "pig" -> HeadPig( onClick = {current = "skull"})
//                            "skull" -> HeadSkull(onClick = {current = "creeper"})
//                            "creeper" -> Creeper(onClick = {current = "pig"})
                        }
                    }
                    Row(modifier = Modifier. fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Button(
                            enabled = current > 0,
                            onClick = { current -= 1 }) {
                            Text(text="Atrás")
                        }
                        Button(
                            enabled = current < 2,
                            onClick = { current += 1 }) {
                            Text(text="Siquiente")
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun HeadPig(context: Context?){
    Column(modifier = Modifier
        .fillMaxSize()
        , verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        Box(modifier = Modifier
            .background(basePink)
            .width(600.dp)
            .height(400.dp)
            .clickable {
                MediaPlayer
                    .create(context, R.raw.pig)
                    .start()
            }){
            Face()
        }
    }
}

@Composable
fun Face(){
    ConstraintLayout(
        modifier = Modifier.fillMaxSize(),
    ) {
        val(Eyes,Nose) = createRefs()
        Eyes(modifier = Modifier.constrainAs(Eyes){
            bottom.linkTo(Nose.top)
        })
        Nose(modifier = Modifier.constrainAs(Nose){
            top.linkTo(parent.top, 70.dp)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })
    }
}

@Composable
fun Eyes(modifier:Modifier){
    Row(modifier = modifier
        .height(50.dp)
        .fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween){
        Eye(left = true)
        Eye(left = false)
    }
}

@Composable
fun Nose(modifier: Modifier){
    Row(modifier = modifier
        .width(180.dp)
        .height(120.dp)
        .background(nosePink),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically){
        Nostrils()
        Nostrils()
    }
}

@Composable
fun Nostrils(){
    Box(modifier = Modifier
        .background(nosePinkDark)
        .width(40.dp)
        .height(40.dp))
}
@Composable
fun Eye(left: Boolean){
    Row(){
        Box(modifier = Modifier
            .background(if (left) Color.Black else Color.White)
            .width(53.dp)
            .height(53.dp))
        Box(modifier = Modifier
            .background(if (left) Color.White else Color.Black)
            .width(53.dp)
            .height(53.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Minecraft19100155Theme() {
       // HeadPig({})
    }
}
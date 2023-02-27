package com.example.minecraft

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.minecraft.ui.theme.MinecraftTheme
import androidx.compose.runtime.*
import com.example.minecraft.ui.theme.HeadPig
import com.example.minecraft.ui.theme.HeadCreeper
import com.example.minecraft.ui.theme.HeadSkull

/*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var current by remember { mutableStateOf("pig") }
            MinecraftTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(verticalArrangement = Arrangement.Center) {
                        Text(text = "Actualmente visualizando: $current",
                            style = TextStyle(
                                fontSize = 20.sp,
                                shadow = Shadow(
                                    color = Color.Magenta,
                                    blurRadius = 3f
                                )
                            )
                            , modifier = Modifier.background(Color.LightGray))
                    when(current){
                        "pig" -> HeadPig (onClick = {current = "skull"})
                        "skull" -> HeadSkull (onClick = {current = "creeper"})
                        "creeper" -> HeadCreeper (onClick = {current = "pig"})
                    }
                    }
                }
            }
        }
    }
}
*/
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var current by remember { mutableStateOf(value = 0) }
            MinecraftTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(modifier = Modifier.verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.Center) {
                        when(current){
                            0 -> HeadPig (Context = applicationContext)
                            1 -> HeadSkull (Context = applicationContext)
                            2 -> HeadCreeper (Context = applicationContext)
                        }
                    }
                    Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Button(
                            enabled = current > 0,
                            onClick = {current -=1}
                        ){
                            Text(text = "Atrás")
                        }
                        Button(
                            enabled = current < 2,
                            onClick = {current +=1}
                        ){
                            Text(text = "Siguiente")
                        }
                    }
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MinecraftTheme {
    }
}
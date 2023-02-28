package com.example.minecraftfaces

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.minecraftfaces.ui.theme.MinecraftFacesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var current by remember { mutableStateOf(0) }
            MinecraftFacesTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Column(
                        verticalArrangement = Arrangement.Center
                    ){
                        Text(text = "CURRENT $current",Modifier.background(Color.Black))
                        when (current){
                            0 -> HeadPig (context = applicationContext)
                            1 -> SkeletonHead (context = applicationContext)
                            2 -> CreeperHead (context = applicationContext)
                        }
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            Button(
                                enabled = current > 0,
                                onClick = {current -= 1})
                            {
                                Text(text = "Atras")
                            }
                            Button(
                                enabled = current < 2,
                                onClick = {current += 1})
                            {
                                Text(text = "Adelante")
                            }
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MinecraftFacesTheme {
        Greeting("Android")
    }
}
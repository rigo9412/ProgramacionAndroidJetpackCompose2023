package com.example.minecraftfaces

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.minecraftfaces.ui.theme.MinecraftFacesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var current by remember { mutableStateOf("pig") }
            MinecraftFacesTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Column(
                        verticalArrangement = Arrangement.Center
                    ){
                        Text(text = "Actual $current",Modifier.background(Color.Black))
                        when(current){
                            "pig" -> HeadPig(onClick = {current = "skull"})
                            "skull" -> SkeletonHead(onClick = {current = "creeper"})
                            "creeper" -> CreeperHead(onClick = {current = "pig"})
                        }
//                        when (current){
//                            0 -> HeadPig {
//                                context = applicationContext
//                            }
//                            1 -> SkeletonHead {
//                                context = applicationContext
//                            }
//                            2 -> CreeperHead {
//                                context = applicationContext
//                            }
//                        }

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
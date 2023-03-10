package com.example.composable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composable.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var currentHead by remember { mutableStateOf(0) }
            var textColor by remember { mutableStateOf(nosePink) }

            ComposableTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Minecraft head: $currentHead",
                            modifier = Modifier
                                .background(textColor)
                                .padding(5.dp),
                            fontSize = 20.sp
                        )
                        when (currentHead) {
                            0 -> {
                                textColor = nosePink
                                Pig(context = applicationContext)
                            }
                            1 -> {
                                textColor = baseGray
                                SkeletonDraw(context = applicationContext)
                            }
                            2 -> {
                                textColor = baseGreen
                                CreeperDraw(context = applicationContext)
                            }
                        }

                        Row(
                            modifier = Modifier.padding(top = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Button({
                                currentHead -= 1
                            }, enabled = currentHead > 0) {
                                Text(text = "Previous")
                            }
                            Button(onClick = {
                                currentHead += 1
                            }, enabled = currentHead < 2) {
                                Text(text = "Next")
                            }
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
    ComposableTheme {
        Surface(color = MaterialTheme.colorScheme.background) {

        }
    }
}
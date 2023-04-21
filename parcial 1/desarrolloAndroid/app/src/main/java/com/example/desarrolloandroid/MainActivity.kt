package com.example.desarrolloandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.desarrolloandroid.ui.theme.DesarrolloAndroidTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import com.example.desarrolloandroid.Headpig
import com.example.desarrolloandroid.HeadSkull


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           var current by rememberSaveable{ mutableStateOf(0) }
//            var current by rememberSaveable{ mutableStateOf("Pig") }
            DesarrolloAndroidTheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   Column(
                       modifier = Modifier
                           .verticalScroll(rememberScrollState()),
                       verticalArrangement = Arrangement.Center,
                       //horizontalAlignment = Alignment.CenterHorizontally
                   ){
//                       Text(text = "CURRENT $current", modifier = Modifier.background(Color.Black))
                       when(current){
//                           "Pig"-> Headpig (onClick = {current="Esqueleto"})
//                           "Esqueleto"-> HeadSkull (onClick = {current="Creeper"})
//                           "Creeper"-> HeadCreeper (onClick = {current="Pig"})
                             0-> Headpig(context = applicationContext)
                             1-> HeadSkull(context = applicationContext)
                            2-> HeadCreeper(context = applicationContext)
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
    DesarrolloAndroidTheme{
       //Headpig()
    }
}
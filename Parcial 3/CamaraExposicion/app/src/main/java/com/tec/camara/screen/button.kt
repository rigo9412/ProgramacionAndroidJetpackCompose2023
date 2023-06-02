package com.tec.camara.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ButtonRow(onTakePhoto: () -> Unit, onTakeVideo: () -> Unit){
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly){
        Button(onClick = onTakePhoto){
            Text("Tomar foto")
        }
        Button(onClick = onTakeVideo){
            Text("Tomar Video")
        }
    }
}
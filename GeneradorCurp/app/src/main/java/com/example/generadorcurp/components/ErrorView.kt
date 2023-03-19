package com.example.generadorcurp.components

import android.media.MediaPlayer
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.generadorcurp.R
import androidx.compose.ui.unit.sp
import com.example.generadorcurp.form.ui.components.btnEnter

@Composable
fun ErrorView(message : String, onInitAction: () -> Unit){
    val color = remember { Animatable(Color.Black)}
    LaunchedEffect(Unit){
        color.animateTo(Color.Red, animationSpec = tween(1000))
        color.animateTo(Color.Black, animationSpec = tween(1000))
    }
    //jeje
    var mediaPlayer = MediaPlayer.create(LocalContext.current, R.raw.test1)
    mediaPlayer.isLooping = true
    mediaPlayer.start()
    
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(30.dp)
        .background(color.value),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = message , fontSize = 26.sp, modifier = Modifier.padding(15.dp))
        Icon(Icons.Default.Warning, modifier = Modifier.size(100.dp), contentDescription = "ERROR CURP", tint = Color.White)
        btnEnter(enabled = true, generar = { onInitAction() }, content = "REINICIAR")

    }
}
package com.example.simondice

import android.media.MediaPlayer
import android.os.Bundle
import android.window.SplashScreen
import android.window.SplashScreenView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.simondice.models.Action
import com.example.simondice.models.Game
import com.example.simondice.models.Player
import com.example.simondice.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(){
    Splash()
}

@Composable
fun Splash(){
    Column( modifier = Modifier.fillMaxSize().background(color = Color.Black), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text(
            text = "¡Fin del juego!",
            fontWeight = FontWeight(900) ,
            color = Color.White,
            fontFamily = FontFamily.Cursive,
            style = TextStyle(
                fontSize = 50.sp,
                shadow = Shadow(
                    color = Color.DarkGray,
                    blurRadius = 3f
                )
            )
        )

    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
Splash()
}





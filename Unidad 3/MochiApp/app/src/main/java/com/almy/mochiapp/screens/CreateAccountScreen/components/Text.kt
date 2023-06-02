package com.almy.mochiapp.screens.CreateAccountScreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.almy.mochiapp.ui.theme.GreenEnabled
import com.almy.mochiapp.ui.theme.LightPurple

@Composable
fun CustomTextClickeable(text: String, onCreateAccountSelected: () -> Unit) {
    Text(
        text = text,
        modifier = Modifier
            .clickable { onCreateAccountSelected()}
            .padding(20.dp),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
}


@Composable
fun CustomTextSubtitle(text: String, modifier: Modifier) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = 16.sp,
        fontWeight = FontWeight.Light,
        color = Color.Black
    )
}

@Composable
fun CustomTextHuella(text: String, isDetected:Boolean, modifier: Modifier) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = 16.sp,
        fontWeight = FontWeight.Light,
        color = if(isDetected)Color.Black else GreenEnabled
    )
}


@Composable
fun CustomTextTitle(text: String, modifier: Modifier) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = LightPurple
    )
}

@Composable
fun CustomTextAssigmentCompleted(text:String, modifier: Modifier){
    Box(
        modifier = modifier
            .size(width = 300.dp, height = 50.dp)
            .background(
                color = LightPurple,
                shape = CircleShape
            )
            .border(
                width = 2.dp,
                color = Color.Transparent,
                shape = CircleShape
            )
            .shadow(
                elevation = 4.dp,
                shape = CircleShape,
                clip = true
            ),
        contentAlignment = Alignment.TopStart
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
    }
}
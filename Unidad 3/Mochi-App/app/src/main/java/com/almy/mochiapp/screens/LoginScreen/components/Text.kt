package com.almy.mochiapp.screens.LoginScreen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material.Text
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.almy.mochiapp.ui.theme.GreenEnabled

@Composable
fun CustomTextClickeable(text: String, onCreateAccountSelected: () -> Unit) {
    Text(
        text = text,
        modifier = Modifier
            .clickable { onCreateAccountSelected()}
            .padding(20.dp)
            .testTag("btnCrearCuenta"),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
}


@Composable
fun CustomTextHuella(text: String, isDetected:Boolean, modifier: Modifier) {
    Text(
        text = text,
        modifier = modifier,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = if(isDetected)Color.Black else GreenEnabled
    )
}
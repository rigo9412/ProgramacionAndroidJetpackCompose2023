package com.almy.mochiapp.screens.CreateAccountScreen.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun Image(ruta:String, modifier: Modifier){
    val bitmap = BitmapFactory.decodeStream(LocalContext.current.assets.open(ruta))
    val painter = BitmapPainter(bitmap.asImageBitmap())
    Image(
        painter = painter,
        modifier = modifier,
        contentDescription = "Imagen"
    )
}
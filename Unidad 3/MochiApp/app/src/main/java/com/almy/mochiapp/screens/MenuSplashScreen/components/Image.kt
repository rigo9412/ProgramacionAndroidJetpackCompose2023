package com.almy.mochiapp.screens.MenuSplashScreen.components

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun Image(ruta:String){
    val bitmap = BitmapFactory.decodeStream(LocalContext.current.assets.open(ruta))
    val painter = BitmapPainter(bitmap.asImageBitmap())
    Image(
        painter = painter,
        contentDescription = "Imagen",
        modifier = Modifier.size(175.dp)
    )
}
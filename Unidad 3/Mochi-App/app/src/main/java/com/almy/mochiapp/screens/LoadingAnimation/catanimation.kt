package com.almy.mochiapp.screens.LoadingAnimation

import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.almy.mochiapp.R

@Composable
fun PantallaDeCarga() {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.animated_cat)
    )

    LottieAnimation(
        composition = composition,
        modifier = Modifier.size(400.dp),
        iterations = Int.MAX_VALUE,
        speed = 1.5f
    )
}

package com.lanazirot.simonsays.presentation.pad_button.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lanazirot.simonsays.domain.model.SimonColorPad

@Composable
fun PadButton(
    color: SimonColorPad,
    isFlashing: Boolean = false,
    enabled: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .background(color = color.color)
            .size(100.dp)
            .clickable(enabled = enabled, onClick = onClick)
    )
}


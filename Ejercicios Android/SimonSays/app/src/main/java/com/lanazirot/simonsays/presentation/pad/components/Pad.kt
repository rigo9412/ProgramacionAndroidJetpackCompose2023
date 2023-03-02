package com.lanazirot.simonsays.presentation.pad.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lanazirot.simonsays.domain.model.SimonColorPad
import com.lanazirot.simonsays.presentation.pad_button.components.PadButton



@Composable
fun Pad(columnScope : @Composable () -> Unit = {}) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        columnScope()
    }
}


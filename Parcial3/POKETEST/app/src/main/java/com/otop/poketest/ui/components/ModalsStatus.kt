package com.otop.poketest.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun ErrorModal(
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(Color(0x99000000))
        ) {
            Column(
                Modifier
                    .padding(16.dp)
                    .align(Alignment.Center)
            ) {
                Text(
                    text = "TE EQUIVOCASTE XD.",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}
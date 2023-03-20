package com.lanazirot.curpgenerator.screens.results.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CURPResultScreen(
    curp: String,
    name: String,
    onClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "CURP: $curp",
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Nombre: $name",
            style = MaterialTheme.typography.h5
        )
        Spacer(modifier = Modifier.height(16.dp))
        Icon(
            imageVector = androidx.compose.material.icons.Icons.Default.Check,
            contentDescription = "Check",
            tint = Color.Green,
            modifier = Modifier.size(64.dp)
        )
        Button(onClick = onClick) {
            Text(text = "Regresar")
        }

    }
}
@Preview(showBackground = true)
@Composable
fun CURPResultScreenPreview() {
    CURPResultScreen(
        curp = "CURP",
        name = "Nombre"
    )
}